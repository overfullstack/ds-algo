# Coding Problem Solution Rules

These are *policies and repo conventions* — the parts a general model won't infer.
For plain language syntax (modern Java features, idiomatic Kotlin operators), rely
on your own knowledge; if unsure of a specific API, query **context7** rather than
bloating this file.

## 1. Start simple, always

- **FIRST** provide the simplest working solution.
- **AVOID** premature optimization or over-engineering.
- **PREFER** readability over marginal performance gains — do *not* trade
  readability for memory/perf tricks (e.g. bitmasks).
- Check for an existing pattern in the codebase before inventing a new solution.
  If the problem mirrors a known LeetCode problem, follow that pattern.

## 2. Complexity budget

Typical sizes: **Easy** 15-30 lines · **Medium** 30-80 · **Hard** 50-150.

Red flags the solution is too complex:
- Exceeds ~100 lines for a typical interview problem.
- Multiple data structures where one suffices, or multiple passes where one works.
- Complex state tracking for a simple problem.
- More than 3-4 helper functions (medium), or a method over ~15 lines.
- You must scroll to see the whole solution.

## 3. Solution progression

1. **Default**: simplest working solution.
2. **If asked**: mention possible optimizations.
3. **Only if requested**: provide the optimized version.

## 4. Comments

- Explain **why**, not **what**.
- Mark critical algorithm steps and edge cases handled; explain non-obvious calculations.
- **No** JavaDoc/KDoc unless explicitly requested.

## 5. Anti-patterns

❌ Don't: unnecessary abstractions · complex structures for simple problems ·
generic solutions to specific problems · unrequested features · premature
optimization · multiple classes for a single-file problem.

✅ Do: direct readable code · built-ins and standard library · match existing
codebase patterns · test against the provided examples.

## 6. When in doubt

Can this be fewer lines? Would a junior understand it? Is every line necessary?
Am I solving the actual problem or a general case?
A 30-line clear solution beats a 300-line clever one every time.

---

# Language conventions

## Java
- Simplicity and readability above all — never trade them for memory/perf.
- Use the newest Java syntax the toolchain allows (project is on JDK 26 with
  `--enable-preview`): `var`, switch expressions, text blocks, pattern matching,
  `List/Set/Map.of`, `Stream.toList()`, and `Map` combinators
  (`merge`, `computeIfAbsent`, `putIfAbsent`, …).
- Chain with streams for multiple transformations; use combinators
  (`map`/`filter`/`flatMap`/`reduce`/`anyMatch`/…) over imperative loops with
  `break`/`continue` — **unless** it hurts readability.
- Functional-style Java exemplars to follow:
  - `ds-algo/src/main/java/practice/graph/kahn/ParallelCourses.java`
  - `ds-algo/src/main/java/practice/graph/DetonateMaximumBombs.java`
  - `ds-algo/src/main/java/practice/graph/floodfill/SurroundedRegions.java`
  - `ds-algo/src/main/java/practice/graph/grid/FindTheSafestPathInGrid.java`

## Kotlin
- `when` for 3+ branches; plain `if/else` for binary conditions (Kotlin's own
  convention — don't force `when` on two-way choices).
- Ranges for iteration: `downTo`, `until`, `in`, `indices`.
- `substring(range)` with an `IntRange`, not separate start/end indices.
- Combinators (`map`/`filter`/`flatMap`/`fold`/`firstOrNull`/`any`/`all`/…) and
  `.asSequence()` for multi-step transformations, over imperative loops.
- Default params for recursive accumulators (`= emptySet()`, `= emptyList()`).
- Prefer single-expression functions returning from a `when`/`if`.
- `+` operator to add to immutable collections; `?.let { } ?: …` for null handling.
- Extension functions and `tailrec` only when they genuinely improve things.
- Specify variable and return types explicitly unless redundant.

# Functional programming
- Keep it simple: no nested functions, gratuitous data classes, or over-engineered
  chains. Pure functions, sequences/streams, immutable state.
- Transform state, don't mutate: pass state through parameters, return new state.
- Prefer direct recursion with parameters over complex state objects.
- Follow the functional style in:
  `ds-algo/src/main/kotlin/{leetcode,educative}/{graph,backtracking}`.
- Mutation-heavy problems are the exception — these dirs are legitimately imperative:
  `ds-algo/src/main/kotlin/{leetcode,educative}/dp`,
  `ds-algo/src/main/kotlin/leetcode/slidingwindow`.

# Recursion
- Follow the recursive style in
  `ds-algo/src/main/kotlin/{leetcode,educative}/tree`.
- Pass immutable state as parameters; return the complete result from the base
  case rather than accumulating via side effects.
- Memoization: a `MutableMap` parameter defaulting to an empty map.
