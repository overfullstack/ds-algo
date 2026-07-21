# Testcase Framework Migration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace 42 per-shape `testcase/*To*.kt` parser classes with one universal envelope model (`TestCases`) whose slot types are supplied at the call site via reified generics.

**Architecture:** One `@Serializable data class TestCases` models the shared `{testcases:[{name,inputs,output}]}` envelope; `inputs`/`output` are `List<Map<String, JsonElement>>`. Reified `Case.input<T>(slot)` / `Case.output<T>(slot)` decode a numbered slot into the caller's type. Each test file keeps its body verbatim and only rewrites the load line to `TestCases.load(paths).map { <fold> }`, where `<fold>` comes from the Translation Table below. Array→`Pair`/`IntArray`/`Set` conversions stay explicit at the call site using existing `utils` + stdlib adapters.

**Tech Stack:** Kotlin, kotlinx-serialization-json 1.11.0, Kotest 6.2.2, Gradle (module `:ds-algo` for tests, `:common` for the model).

## Global Constraints

- Model file lives in module `common`: `common/src/main/kotlin/testcase/TestCases.kt`. Tests live in module `ds-algo` under `ds-algo/src/test/kotlin`.
- Slots are **1-based**, matching existing JSON keys (`"1"`, `"2"`, …). **No JSON fixture is edited** in this migration.
- `Json` config must be `Json { explicitNulls = false; ignoreUnknownKeys = true }` (matches the old classes that used `explicitNulls = false`).
- Path string arguments to `load(...)` are copied **verbatim** from each old `parseJsonFileToTestCases(...)` call — never change them.
- Adapters: `.toPair()` on a `List`/`IntArray` comes from `utils` (`import utils.toPair`). `.toIntArray()`, `.toSet()`, `.toTypedArray()`, `.zip()`, `.map` are Kotlin stdlib — **no import**.
- Out of scope, do not touch: `LRUCacheArgs.kt`, `ds.tree.TreeNode.parseJsonFileToTree`, graph parsers, and the 23 domain-shaped (non-envelope) JSON fixtures.
- Test run command (scoped): `./gradlew :ds-algo:test --tests "<pattern>"`. Full run: `./gradlew :ds-algo:test`.

---

## Translation Table (authoritative — exact fold per class)

Each row is the lambda body for `TestCases.load(<same paths>).map { case -> <ROW> }`. `case` is a `TestCases.Case`. The produced tuple type is identical to the old parser's element type, so every test's `.forAll { ... }` body is preserved unchanged. Rows marked **toPair** require `import utils.toPair` in the migrated test file.

| Class | Fold expression (map lambda body) | toPair |
|---|---|---|
| `IntToInt` | `case.input<Int>(1) to case.output<Int>(1)` | |
| `IntIntToList` | `Triple(case.input<Int>(1), case.input<Int>(2), case.output<List<Int>>(1))` | |
| `IntIntListListToInt` | `Triple(case.input<Int>(1), case.input<Int>(2), case.input<List<Int>>(3).zip(case.input<List<Int>>(4))) to case.output<Int>(1)` | |
| `IntIntLListToInt` | `Triple(case.input<Int>(1), case.input<Int>(2), case.input<List<List<Int>>>(3).map { it.toPair() }.toSet()) to case.output<Int>(1)` | ✅ |
| `IntListListToInt` | `Triple(case.input<Int>(1), case.input<List<Int>>(2), case.input<List<Int>>(3)) to case.output<Int>(1)` | |
| `IntListPairToInt` | `(case.input<Int>(1) to case.input<List<List<Int>>>(2).map { it.toPair() }) to case.output<Int>(1)` | ✅ |
| `List_Str_IntToInt` | `Triple(case.input<List<String>>(1), case.input<Int>(2), case.output<Int>(1))` | |
| `List3ToInt` | `Triple(case.input<List<Int>>(1).toIntArray(), case.input<List<Int>>(2).toIntArray(), case.input<List<Int>>(3).toIntArray()) to case.output<Int>(1)` | |
| `ListIntToInt` | `(case.input<List<Int>>(1) to case.input<Int>(2)) to case.output<Int>(1)` | |
| `ListIntToList` | `Triple(case.input<List<Int>>(1), case.input<Int>(2), case.output<List<Int>>(1))` | |
| `ListListIntToLList` | `Triple(case.input<List<Int>>(1), case.input<List<Int>>(2), case.input<Int>(3)) to case.output<List<List<Int>>>(1)` | |
| `ListListToList` | `(case.input<List<Int>>(1) to case.input<List<Int>>(2)) to case.output<List<Int?>>(1)` | |
| `ListPairIntToInt` | `Triple(case.input<List<List<Int>>>(1).map { it.toPair() }, case.input<Int>(2), case.output<Int>(1))` | ✅ |
| `ListPairToBoolean` | `case.input<List<List<Int>>>(1).map { it.toPair() } to case.output<Boolean>(1)` | ✅ |
| `ListPairToInt` | `case.input<List<List<Int>>>(1).map { it.toPair() } to case.output<Int>(1)` | ✅ |
| `ListStrToBool` | `Triple(case.input<List<String>>(1), case.input<String>(2), case.output<Boolean>(1))` | |
| `ListToBool` | `case.input<List<Int>>(1) to case.output<Boolean>(1)` | |
| `ListToInt` | `case.input<List<Int?>>(1) to case.output<Int>(1)` | |
| `ListToList` | `case.input<List<Int?>>(1) to case.output<List<Int>>(1)` | |
| `ListToLList` | `case.input<List<Int?>>(1) to case.output<List<List<Int>>>(1)` | |
| `LListIntIntIntToLList` | `Triple(case.input<List<List<Int>>>(1), Triple(case.input<Int>(2), case.input<Int>(3), case.input<Int>(4)), case.output<List<List<Int>>>(1).map { it.toIntArray() }.toTypedArray())` | |
| `LListIntIntToInt` | `Triple(case.input<List<List<Int>>>(1), case.input<Int>(2), case.input<Int>(3)) to case.output<Int>(1)` | |
| `LListIntToInt` | `Triple(case.input<List<List<Int>>>(1), case.input<Int>(2), case.output<Int>(1))` | |
| `LListListToInt` | `Triple(case.input<List<List<Int>>>(1), case.input<List<Int>>(2).toSet(), case.output<Int>(1))` | |
| `LListListToLList` | `(case.input<List<List<Int>>>(1).map { it.toPair() } to case.input<List<Int>>(2).toPair()) to case.output<List<List<Int>>>(1).map { it.toPair() }` | ✅ |
| `LListLListToLList` | `(case.input<List<List<Int>>>(1).map { it.toPair() } to case.input<List<List<Int>>>(2).map { it.toPair() }) to case.output<List<List<Int>>>(1).map { it.toPair() }` | ✅ |
| `LListStrToBool` | `Triple(case.input<List<List<String>>>(1), case.input<String>(2), case.output<Boolean>(1))` | |
| `LListStrToInt` | `case.input<List<List<String>>>(1).map { it.map { it.toInt() }.toIntArray() }.toTypedArray() to case.output<Int>(1)` | |
| `LListToInt` | `Pair(case.input<List<List<Int>>>(1).map { it.toPair() }.toSet(), case.output<Int>(1))` | ✅ |
| `LListToList` | `case.input<List<List<Int>>>(1).map { it.toPair() } to case.output<List<Int>>(1).toPair()` | ✅ |
| `LLListToLList` | `case.input<List<List<List<Int>>>>(1).map { it.map { it.toPair() } } to case.output<List<List<Int>>>(1).map { it.toPair() }` | ✅ |
| `MatrixToMatrix` | `case.input<List<List<Int>>>(1) to case.output<List<List<Int>>>(1).map { it.toPair() }.toSet()` | ✅ |
| `StrIntToList` | `Triple(case.input<String>(1), case.input<Int>(2), case.output<List<String>>(1))` | |
| `StrListToList` | `(case.input<String>(1) to case.input<List<String>>(2)) to case.output<List<String>>(1)` | |
| `StrStrToBool` | `Triple(case.input<String>(1), case.input<String>(2), case.output<Boolean>(1))` | |
| `StrStrToList` | `Triple(case.input<String>(1), case.input<String>(2), case.output<List<Int>>(1))` | |
| `StrStrToStr` | `Triple(case.input<String>(1), case.input<String>(2), case.output<String>(1))` | |
| `StrToBool` | `case.input<String>(1) to case.output<Boolean>(1)` | |
| `StrToInt` | `case.input<String>(1) to case.output<Int>(1)` | |
| `StrToStr` | `case.input<String>(1) to case.output<String>(1)` | |

**Dead classes (no call sites) — deleted, never migrated:** `ListStrToLList`, `ListLListToList`.

**Slot-equivalence note (proved before planning):** old `ListToList` folded `it.inputs.flatMap { it.x1 }` / `it.output.flatMap { it.x1 }`, and old `ListIntToList` folded `it.output.flatMap { it.x1 }`. A scan of all 89 envelope fixtures found **zero** testcases with more than one output entry and **zero** with duplicate input slot keys, so `flatMap` over the single entry is identical to single-slot access. The table's single-slot expressions are exact replacements.

---

## Mechanical transform (applied identically to every migrated test file)

Given a test file that currently does:

```kotlin
import testcase.<OldClass>.Companion.parseJsonFileToTestCases
...
      parseJsonFileToTestCases("<pathA>", "<pathB>").forAll { <destructure> -> <body> }
```

Apply exactly three edits — nothing else in the file changes:

1. Replace the import line with `import testcase.TestCases`.
2. Replace `parseJsonFileToTestCases(<paths>)` with `TestCases.load(<paths>).map { case -> <ROW> }`, where `<paths>` is copied verbatim and `<ROW>` is the Translation Table row for `<OldClass>`.
3. If the row is marked **toPair**, ensure `import utils.toPair` is present (add if missing).

The `.forAll { <destructure> -> <body> }` is left byte-for-byte identical because `<ROW>` reproduces the old element type.

---

## Task 1: Universal `TestCases` model

**Files:**
- Create: `common/src/main/kotlin/testcase/TestCases.kt`
- Test: `ds-algo/src/test/kotlin/testcase/TestCasesTest.kt`

**Interfaces:**
- Produces: `testcase.TestCases`, with:
  - `companion.load(vararg paths: String): List<TestCases.Case>`
  - `companion.json: Json`
  - `inline fun <reified T> TestCases.Case.input(slot: Int): T`
  - `inline fun <reified T> TestCases.Case.output(slot: Int): T`
  - `TestCases.Case(name: String, inputs: List<Map<String, JsonElement>>, output: List<Map<String, JsonElement>>)`

- [ ] **Step 1: Write the failing test**

Create `ds-algo/src/test/kotlin/testcase/TestCasesTest.kt`:

```kotlin
package testcase

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class TestCasesTest :
  StringSpec({
    "decodes numbered slots into caller-requested types" {
      val raw =
        """{"testcases":[{"name":"t","inputs":[{"1":[1,2,3]},{"2":2}],"output":[{"1":6}]}]}"""
      val cases = TestCases.json.decodeFromString<TestCases>(raw).testcases
      cases shouldHaveSize 1
      val case = cases.first()
      case.name shouldBe "t"
      case.input<List<Int>>(1) shouldBe listOf(1, 2, 3)
      case.input<Int>(2) shouldBe 2
      case.output<Int>(1) shouldBe 6
    }

    "load reads a fixture file and flattens testcases" {
      val cases = TestCases.load("educative/fusion/MeetingRooms2/test-cases-1.json")
      cases shouldHaveSize 8
      // ListPairToInt-shaped: input slot 1 is List<List<Int>>, output slot 1 is Int
      cases.first().input<List<List<Int>>>(1).first() shouldBe listOf(2, 5, 7)
      cases.first().output<Int>(1) shouldBe 2
    }
  })
```

- [ ] **Step 2: Run test to verify it fails**

Run: `./gradlew :ds-algo:test --tests "testcase.TestCasesTest"`
Expected: FAIL — compilation error, `TestCases` is unresolved.

- [ ] **Step 3: Write minimal implementation**

Create `common/src/main/kotlin/testcase/TestCases.kt`:

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
    val inputs: List<Map<String, JsonElement>>,
    val output: List<Map<String, JsonElement>>,
  ) {
    /** Decode the 1-based input [slot] (matching the JSON key) into the caller-supplied type. */
    inline fun <reified T> input(slot: Int): T = decode(inputs[slot - 1].getValue("$slot"))

    /** Decode the 1-based output [slot] into the caller-supplied type. */
    inline fun <reified T> output(slot: Int): T = decode(output[slot - 1].getValue("$slot"))
  }

  companion object {
    val json = Json {
      explicitNulls = false
      ignoreUnknownKeys = true
    }

    inline fun <reified T> decode(element: JsonElement): T = json.decodeFromJsonElement(element)

    /** Load and flatten testcases across one or more fixture paths. */
    fun load(vararg paths: String): List<Case> =
      paths.flatMap { json.decodeFromString<TestCases>(readFileToString(it)).testcases }
  }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `./gradlew :ds-algo:test --tests "testcase.TestCasesTest"`
Expected: PASS (2 tests).

- [ ] **Step 5: Commit**

```bash
git add common/src/main/kotlin/testcase/TestCases.kt ds-algo/src/test/kotlin/testcase/TestCasesTest.kt
git commit -m "feat(testcase): add universal TestCases envelope model"
```

---

## Migration tasks (Tasks 2–8)

Each migration task applies the **Mechanical transform** to the listed files. The old parser classes are NOT deleted yet — they coexist so the module keeps compiling while unmigrated files still reference them (deletion is Task 9). After edits, run the scoped test command and commit.

Worked example (used for every file — shown once here): `educative/heap/MaximizeCapitalTest.kt` uses `IntIntListListToInt`.

Before:
```kotlin
import testcase.IntIntListListToInt.Companion.parseJsonFileToTestCases
...
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        val (initialCapital, k, capitalsToProfits) = input
        maximizeCapital(initialCapital, k, capitalsToProfits) shouldBe output
      }
```
After:
```kotlin
import testcase.TestCases
...
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(
            case.input<Int>(1),
            case.input<Int>(2),
            case.input<List<Int>>(3).zip(case.input<List<Int>>(4)),
          ) to case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (initialCapital, k, capitalsToProfits) = input
          maximizeCapital(initialCapital, k, capitalsToProfits) shouldBe output
        }
```
Note: `IntIntListListToInt` is not **toPair**-marked, so no `utils` import. The `.forAll` block is unchanged.

### Task 2: Migrate `educative/dp` (8 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/dp/01KnapsackTest.kt` | `IntListListToInt` | |
| `educative/dp/MaxProfitInJobSchedulingTest.kt` | `List3ToInt` | |
| `educative/dp/MinCoinsForSumTest.kt` | `ListIntToInt` | |
| `educative/dp/LongestIncreasingSubsequenceTest.kt` | `ListToInt` | |
| `educative/dp/MaxProductSubarrayTest.kt` | `ListToInt` | |
| `educative/dp/PartitionEqualSubsetSumTest.kt` | `ListToBool` | |
| `educative/dp/DecodeWaysTest.kt` | `StrToInt` | |
| `educative/dp/LongestPalindromicSubstringTest.kt` | `StrToStr` | |

- [ ] **Step 1: Apply the Mechanical transform to each file above**, using the Translation Table row for its class. Copy path arguments verbatim. None here are toPair-marked.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.dp.*"`
Expected: PASS — same tests green as before migration.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/dp
git commit -m "refactor(testcase): migrate educative/dp to TestCases model"
```

### Task 3: Migrate `educative/fusion` (8 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/fusion/TaskSchedulerTest.kt` | `List_Str_IntToInt` | |
| `educative/fusion/KPairsWithSmallestSumTest.kt` | `ListListIntToLList` | |
| `educative/fusion/MeetingRoomsTest.kt` | `ListPairToBoolean` | ✅ |
| `educative/fusion/MeetingRooms2Test.kt` | `ListPairToInt` | ✅ |
| `educative/fusion/KthSmallestInSortedListsTest.kt` | `LListIntToInt` | |
| `educative/fusion/InsertIntervalTest.kt` | `LListListToLList` | ✅ |
| `educative/fusion/IntervalListIntersectionsTest.kt` | `LListLListToLList` | ✅ |
| `educative/fusion/EmployeeFreeTimeTest.kt` | `LLListToLList` | ✅ |

- [ ] **Step 1: Apply the Mechanical transform to each file.** For toPair-marked rows, ensure `import utils.toPair` is present.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.fusion.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/fusion
git commit -m "refactor(testcase): migrate educative/fusion to TestCases model"
```

### Task 4: Migrate `educative/elegant/unionfind` (7 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/elegant/unionfind/LastDayToCrossTest.kt` | `IntIntLListToInt` | ✅ |
| `educative/elegant/unionfind/ConnectedInUndirectedGraphTest.kt` | `IntListPairToInt` | ✅ |
| `educative/elegant/unionfind/LongestConsecutiveSequenceTest.kt` | `ListToInt` | |
| `educative/elegant/unionfind/MinimizeMalwareTest.kt` | `LListListToInt` | |
| `educative/elegant/unionfind/NumberOfIslandsTest.kt` | `LListStrToInt` | |
| `educative/elegant/unionfind/MostStonesRemovedTest.kt` | `LListToInt` | ✅ |
| `educative/elegant/unionfind/RedundantConnectionTest.kt` | `LListToList` | ✅ |

- [ ] **Step 1: Apply the Mechanical transform to each file.** Add `import utils.toPair` for the toPair-marked rows.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.elegant.unionfind.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/elegant/unionfind
git commit -m "refactor(testcase): migrate educative/elegant/unionfind to TestCases model"
```

### Task 5: Migrate `educative/tree` + `educative/ll` (8 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/tree/TreeFromPreOrderAndInOrderTest.kt` | `ListListToList` | |
| `educative/tree/HouseRobber3Test.kt` | `ListToInt` | |
| `educative/tree/RightSideViewTest.kt` | `ListToList` | |
| `educative/tree/VerticalOrderTraversalTest.kt` | `ListToLList` | |
| `educative/ll/RemoveKthToLastTest.kt` | `ListIntToList` | |
| `educative/ll/ReverseNodesInKGroupsTest.kt` | `ListIntToList` | |
| `educative/ll/ReorderListTest.kt` | `ListToList` | |
| `educative/ll/ReverseNodesInEvenGroupsTest.kt` | `ListToList` | |

- [ ] **Step 1: Apply the Mechanical transform to each file.** None here are toPair-marked.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.tree.*" --tests "educative.ll.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/tree ds-algo/src/test/kotlin/educative/ll
git commit -m "refactor(testcase): migrate educative/tree and educative/ll to TestCases model"
```

### Task 6: Migrate `educative/heap` + `educative/twopointers` (7 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/heap/MaximizeCapitalTest.kt` | `IntIntListListToInt` | |
| `educative/heap/KClosestPointsToOriginTest.kt` | `ListPairIntToInt` | ✅ |
| `educative/heap/ScheduleTasksOnMinimumMachinesTest.kt` | `ListPairToInt` | ✅ |
| `educative/heap/AllAnagramsTest.kt` | `StrStrToList` | |
| `educative/twopointers/FirstBadVersionTest.kt` | `IntIntToList` | |
| `educative/twopointers/ContainerWithMostWaterTest.kt` | `ListToInt` | |
| `educative/twopointers/SortColorsTest.kt` | `ListToList` | |

Note: `KClosestPointsToOriginTest.kt` has two blocks — a `forAll(row(...))` literal block and a `parseJsonFileToTestCases(...)` block. Migrate **only** the `parseJsonFileToTestCases` call; leave the `io.kotest.data.forAll` row block untouched.

- [ ] **Step 1: Apply the Mechanical transform to each file.** Add `import utils.toPair` for the two toPair-marked rows.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.heap.*" --tests "educative.twopointers.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/heap ds-algo/src/test/kotlin/educative/twopointers
git commit -m "refactor(testcase): migrate educative/heap and educative/twopointers to TestCases model"
```

### Task 7: Migrate `educative/sorting` + `educative/graph` + `educative/backtracking` (9 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/sorting/cyclicsort/FirstMissingPositiveNumberTest.kt` | `ListToInt` | |
| `educative/sorting/cyclicsort/MissingNumberTest.kt` | `ListToInt` | |
| `educative/sorting/cyclicsort/CorruptPairTest.kt` | `ListToList` | |
| `educative/graph/BusRoutesTest.kt` | `LListIntIntToInt` | |
| `educative/graph/NetworkDelayTimeTest.kt` | `LListIntIntToInt` | |
| `educative/graph/PacificAtlanticWaterFlowTest.kt` | `MatrixToMatrix` | ✅ |
| `educative/backtracking/NQueensTest.kt` | `IntToInt` | |
| `educative/backtracking/FloodFillTest.kt` | `LListIntIntIntToLList` | |
| `educative/backtracking/WordSearchInGridTest.kt` | `LListStrToBool` | |

- [ ] **Step 1: Apply the Mechanical transform to each file.** Add `import utils.toPair` for `PacificAtlanticWaterFlowTest.kt`.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.sorting.*" --tests "educative.graph.*" --tests "educative.backtracking.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/sorting ds-algo/src/test/kotlin/educative/graph ds-algo/src/test/kotlin/educative/backtracking
git commit -m "refactor(testcase): migrate educative/sorting, graph, backtracking to TestCases model"
```

### Task 8: Migrate `educative/array` + `educative/hashing` + `educative/stack` + `leetcode` (10 files)

**Files (Modify):**

| Test file | Old class | toPair import |
|---|---|---|
| `educative/array/VerifyAlienDictionaryTest.kt` | `ListStrToBool` | |
| `educative/array/slidingwindow/RepeatedDNASequencesTest.kt` | `StrIntToList` | |
| `educative/array/slidingwindow/MinWindowSubsequenceTest.kt` | `StrStrToStr` | |
| `educative/hashing/NextGreaterElementTest.kt` | `ListListToList` | |
| `educative/hashing/RansomNoteTest.kt` | `StrStrToBool` | |
| `educative/stack/ValidParenthesesTest.kt` | `StrToBool` | |
| `educative/stack/RemoveAdjacentDuplicatesTest.kt` | `StrToStr` | |
| `leetcode/array/slidingwindow/SlidingWindowMaxTest.kt` | `ListIntToList` | |
| `leetcode/stack/LargestRectangleInHistogramTest.kt` | `ListToInt` | |
| `leetcode/backtracking/WordBreakSentencesTest.kt` | `StrListToList` | |

- [ ] **Step 1: Apply the Mechanical transform to each file.** None here are toPair-marked.
- [ ] **Step 2: Run the scoped tests**

Run: `./gradlew :ds-algo:test --tests "educative.array.*" --tests "educative.hashing.*" --tests "educative.stack.*" --tests "leetcode.*"`
Expected: PASS.

- [ ] **Step 3: Commit**

```bash
git add ds-algo/src/test/kotlin/educative/array ds-algo/src/test/kotlin/educative/hashing ds-algo/src/test/kotlin/educative/stack ds-algo/src/test/kotlin/leetcode
git commit -m "refactor(testcase): migrate educative/array, hashing, stack and leetcode to TestCases model"
```

---

## Task 9: Delete obsolete parser classes and verify full suite

**Files (Delete):** all 42 obsolete parser classes in `common/src/main/kotlin/testcase/` (every `*.kt` except `TestCases.kt` and `LRUCacheArgs.kt`), which includes the two dead classes `ListStrToLList.kt` and `ListLListToList.kt`.

- [ ] **Step 1: Confirm no remaining references to the old parsers**

Run: `grep -rn "parseJsonFileToTestCases" ds-algo/src/test/kotlin`
Expected: **no output** (every call site migrated).

Run: `grep -rln "import testcase\.[A-Za-z].*\.Companion" ds-algo/src/test/kotlin`
Expected: **no output** except any `LRUCacheArgs` import (out of scope — leave it).

- [ ] **Step 2: Delete the obsolete classes**

```bash
cd common/src/main/kotlin/testcase
ls | grep -vE '^(TestCases|LRUCacheArgs)\.kt$' | xargs git rm
cd -
```

- [ ] **Step 3: Run the full test suite**

Run: `./gradlew :ds-algo:test`
Expected: BUILD SUCCESSFUL — the full pre-migration set of tests passes. If compilation fails with an unresolved `testcase.*` reference, a call site was missed; migrate it via the Mechanical transform, then re-run.

- [ ] **Step 4: Confirm class count**

Run: `ls common/src/main/kotlin/testcase`
Expected: exactly `TestCases.kt` and `LRUCacheArgs.kt`.

- [ ] **Step 5: Commit**

```bash
git add -A common/src/main/kotlin/testcase
git commit -m "refactor(testcase): remove 42 obsolete per-shape parser classes"
```

---

## Self-Review

**Spec coverage:**
- Universal model + reified decoder → Task 1. ✅
- Delete all in-scope classes → Task 9 (42 classes; `LRUCacheArgs` + domain parsers retained). ✅
- Big-bang all ~57 call sites → Tasks 2–8 (dead classes have none). ✅
- Explicit `.map` adapters from `utils`/stdlib → Translation Table + toPair-import column. ✅
- 1-based slots, no fixture edits → Global Constraints + model uses `getValue("$slot")`. ✅
- Out-of-scope untouched (LRU, tree/graph domain parsers) → Global Constraints + Task 9 exclusion. ✅

**Placeholder scan:** No TBD/TODO; every fold expression is literal in the Translation Table; the mechanical transform and one full worked example are concrete. ✅

**Type consistency:** `load`, `input<T>(slot)`, `output<T>(slot)`, `json`, `Case` names are identical across Task 1's Interfaces, the model code, and every migration usage. Each table row's produced tuple type matches the destructuring in its test file's preserved `.forAll` body (verified against `MaximizeCapital`/`KClosestPointsToOrigin`/`RemoveKthToLast` call sites). ✅

**Count check:** 8+8+7+8+7+9+10 = 57 migrated files across Tasks 2–8, matching the class→file inventory. ✅
