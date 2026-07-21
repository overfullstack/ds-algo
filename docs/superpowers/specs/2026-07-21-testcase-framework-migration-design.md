# Testcase framework migration — universal envelope + call-site types

**Date:** 2026-07-21
**Status:** Approved design, pending spec review

## Problem

`common/src/main/kotlin/testcase/` holds **43 hand-written classes**, one per
`(input types → output type)` signature (`IntToInt`, `LListIntIntIntToLList`,
`ListPairToInt`, …). Each new problem with a novel signature forces a new class.
Growth is combinatorial and unbounded: 43 classes, 89 envelope JSON fixtures, 58
call sites today.

Every one of these classes re-declares the **same JSON envelope**:

```json
{ "testcases": [ { "name": "...", "inputs": [ {"1": ...}, {"2": ...} ], "output": [ {"1": ...} ] } ] }
```

The only thing that varies between classes is the **leaf type** of each numbered
slot. So each class exists purely to pin different leaf types onto an identical
structure — that is the waste. The type knowledge belongs at the call site (the
test already knows its own function signature), not in a proliferating class set.

A secondary detail: a few classes do real **adaptation**, not just decoding —
JSON `[[0,30],[5,10]]` becomes `List<Pair<Int,Int>>` via `toPair()`, or
`Array<IntArray>`. kotlinx cannot decode a JSON array into a Kotlin `Pair`
by default, so these conversions must be expressed explicitly. The adapters
(`toPair`, `toIntArray`, `toTriple`) **already exist** in
`common/src/main/kotlin/utils/Extensions.kt` and are reusable.

## Goal

Replace the per-shape class pattern with **one universal envelope model** plus a
**generic, reified decoder**. New shapes then cost **zero new files** — the test
names its own types inline. Delete all in-scope classes. Keep type checking
(via reified generics) and keep adaptation explicit at the call site.

## Scope

**In scope — the numbered-slot envelope classes.** 42 of the 43 files follow the
`{testcases:[{name, inputs:[{"1":..},{"2":..}], output:[{"1":..}]}]}` shape
(including `LListToList`, which merely names its inner class `Inputs`). These +
their ~58 call sites migrate.

**Out of scope — domain-shaped fixtures.** Tree fixtures decode through
`ds.tree.TreeNode.parseJsonFileToTree`, graph through their own parser, and
`LRUCacheArgs` uses a bespoke `{classMethodsToCall, maxSize}` shape. These are
**not** numbered-slot envelopes, do not proliferate, and are left untouched. The
23 non-envelope JSON files belong to this group.

## Design

### Universal model (new)

New file `common/src/main/kotlin/testcase/TestCases.kt` replaces all 42 in-scope
classes:

```kotlin
package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@Serializable
data class TestCases(val testcases: List<Case>) {
  @Serializable
  data class Case(
    val name: String,
    val inputs: List<Map<String, JsonElement>>,   // [{"1": ..}, {"2": ..}]
    val output: List<Map<String, JsonElement>>,   // [{"1": ..}]
  ) {
    /** Decode input slot (1-based, matching the JSON key) into caller-supplied type. */
    inline fun <reified T> input(slot: Int): T = decode(inputs[slot - 1].getValue("$slot"))

    /** Decode output slot (1-based) into caller-supplied type. */
    inline fun <reified T> output(slot: Int): T = decode(output[slot - 1].getValue("$slot"))
  }

  companion object {
    val json = Json { explicitNulls = false; ignoreUnknownKeys = true }

    inline fun <reified T> decode(e: JsonElement): T = json.decodeFromJsonElement(e)

    /** Load and flatten testcases across one or more fixture paths. */
    fun load(vararg paths: String): List<Case> =
      paths.flatMap { json.decodeFromString<TestCases>(readFileToString(it)).testcases }
  }
}
```

Notes:
- Slots stay **1-based** to match existing JSON keys, so **no fixture edits**.
- `inputs`/`output` are `List<Map<String, JsonElement>>` — mirrors the current
  `[{"1":..},{"2":..}]` layout exactly; the map key equals the slot number.
- Type mismatch surfaces as a kotlinx deserialization error at the slot — same
  failure class as today's `!!` + cast, no weaker.

### Call-site pattern (per selected options: explicit adapters)

Simple case (was `IntToInt`):
```kotlin
TestCases.load(path).forAll { case ->
  climbStairs(case.input<Int>(1)) shouldBe case.output<Int>(1)
}
```

Adapter case (was `ListPairToInt`, MeetingRooms2) — `.map { it.toPair() }` visible:
```kotlin
TestCases.load("$PKG_PATH/test-cases-1.json").forAll { case ->
  val meetings = case.input<List<List<Int>>>(1).map { it.toPair() }
  minMeetingRoomsRequired(meetings.toTypedArray()) shouldBe case.output<Int>(1)
}
```

Multi-input case (was `LListIntIntIntToLList`):
```kotlin
TestCases.load(path).forAll { case ->
  val graph = case.input<List<List<Int>>>(1)
  val src   = case.input<Int>(2)
  val dst   = case.input<Int>(3)
  ...
}
```

Adapters (`toPair`, `toIntArray`, `toTriple`) are reused from
`utils/Extensions.kt` — no new adapter code. No typed convenience helpers, no
custom serializers (explicit `.map` chosen for full visibility).

## Migration (big-bang, all 58 sites)

1. Add `TestCases.kt`.
2. Migrate all ~58 call sites to `TestCases.load(...)` + `case.input/output<T>()`,
   translating each old typed return into inline generics + explicit adapters.
3. Delete the 42 in-scope `*To*.kt` classes. Keep `LRUCacheArgs.kt` and any
   domain parsers.
4. Run the full test suite; confirm green (same pass/fail as pre-migration).
5. Single reviewable diff, clean end state, no dual-maintenance window.

## Verification

- **Baseline:** capture full `./gradlew :ds-algo:test` result before migration.
- **Per-migration:** the reified generic must match the algorithm's parameter
  types; a wrong type fails to decode → caught at test run.
- **Post-migration:** full suite green, identical set of passing tests. Grep
  confirms zero remaining references to deleted classes.

## Trade-offs (accepted)

| | Chosen | Rejected |
|---|---|---|
| Type location | Call-site reified generics | KSP codegen (reintroduces per-shape artifact); consolidate-only (doesn't stop growth) |
| Migration | Big-bang all 58 | Incremental (dual systems); new-only (proliferation never shrinks) |
| Adapters | Explicit `.map` at call site | Typed helpers; custom serializers |

**Cost of chosen path:** array→`Pair`/`IntArray` conversion moves from
hidden-in-class to a visible `.map { it.toPair() }` at the call site — the only
verbosity increase, and it makes adaptation explicit. Large single migration
diff, mitigated by mechanical uniformity and full-suite verification.

## Why not KSP

KSP generates code you'd otherwise hand-write. This design hand-writes nothing
per shape — the envelope is one universal model; leaf types come from the call
site's generic parameter. Nothing remains to generate. KSP would still require
declaring each shape somewhere for the processor to consume, so it never beats
"declare nothing," while adding a build-time processor to maintain.
