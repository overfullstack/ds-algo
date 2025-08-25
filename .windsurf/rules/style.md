---
trigger: always_on
description: 
globs: 
---

# Functional Programming Patterns
- Keep functional code simple: avoid nested functions, unnecessary data classes, and over-engineered chains
- Use this codebase's functional style: pure functions, sequences/streams, immutable state
- Reduce state mutation as much as possible. Prefer state transformation
- Build immutable data flow: pass state through parameters, return new state instead of mutating
- Prefer direct recursion with parameters over complex state objects
- Follow the functional style from code in these directories from this code repository:
  - ds-algo/src/main/kotlin/leetcode/graph
  - ds-algo/src/main/kotlin/leetcode/backtracking
  - ds-algo/src/main/kotlin/educative/graph
  - ds-algo/src/main/kotlin/educative/backtracking
- Functional programming style may not apply to some problems that need mutation-heavy solutions. For example, solutions in these directories:
   - ds-algo/src/main/kotlin/leetcode/dp
   - ds-algo/src/main/kotlin/educative/dp
   - ds-algo/src/main/kotlin/leetcode/slidingwindow

# Recursion Patterns
- Follow the recursive style from code in these directories from this code repository:
  - ds-algo/src/main/kotlin/leetcode/tree
  - ds-algo/src/main/kotlin/educative/tree
- For recursive functions with state, pass immutable state as parameters
- Return complete result from base case, not accumulate via side effects
- Cache/memoization: use MutableMap parameter with a default empty map   

# Java-Specific Patterns
- Use latest Java syntax
- Chain operations using stream: prefer `.stream()` for multiple transformations
- If applicable, use functional combinators: stream, map, filter, flatMap, reduce, max, min, findFirst, findAny, anyMatch, allMatch, sum, etc, over imperative loops

# Kotlin-Specific Patterns
- Always use `when` expressions over if-else chains
- Use ranges: `downTo`, `until`, `in`, `indices` for iterations
- String operations: use `substring(range)` with IntRange, not separate indices
- Chain operations using sequences: prefer `.asSequence()` for multiple transformations
- If applicable, use functional combinators: map, filter, flatMap, fold, maxOrNull, minOrNull, firstOrNull, any, all, count, sum, etc, over imperative loops
- Default parameters for recursive accumulators: use `= emptySet()`, `= emptyList()`
- Prefer single-expression functions that return directly from when/if expressions
- Collection operations: use `+` operator for adding to immutable collections
- Use `?.let { }` with elvis `?:` for clean null handling
- Use Extension functions only if they improve readability. Don't overuse
- Use `tailrec` only when the recursive call is truly in tail position
- While generating Kotlin code, specify all variable types and functional return types explicitly, unless redundant.