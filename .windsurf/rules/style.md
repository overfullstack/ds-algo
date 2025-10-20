---
trigger: always_on
description: 
globs: 
---

# CRITICAL: Coding Problem Rules
- **MUST FOLLOW**: For all coding problems, follow `.cursor/rules/coding-problem-rules.md`
- **Start Simple**: Default to simplest solution (20-50 lines typical)
- **Avoid Over-Engineering**: No unnecessary abstractions or complex patterns
- **Pattern Match First**: Check for existing solutions (e.g., NumberOfClosedIslands.java)
- **Typical Sizes**: Easy: 15-30 lines, Medium: 30-80 lines, Hard: 50-150 lines

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
- Prioritize **simplicity** and **readability** above all else. Do not trade readability for memory or performance efficiency, such as using Bitmask.
- Use **Modern and Latest Java syntax available**. Some examples are:
  - Use `IO.println` instead of the legacy `System.out.println`
  - Use HashMap operations like `merge`, `computeIfPresent`, `computeIfAbsent`, `putIfPresent`, `putIfAbsent` etc. 
  - **Modern Collection Operations**: Use `.toList()` instead of `.collect(Collectors.toList())`.
  - **Local Variable Type Inference**: Use `var` for local variables when the type is obvious from context
  - **Enhanced Switch**: Use switch expressions with `->` syntax instead of traditional switch statements
  - **Text Blocks**: Use `"""` text blocks for multi-line strings
  - **Pattern Matching**: Use pattern matching for `instanceof`
  - **Collection Factory Methods**: Use `List.of()`, `Set.of()`, `Map.of()` for immutable collections
- Chain operations using stream: prefer `.stream()` for multiple transformations
- If applicable, use functional combinators: map, filter, flatMap, reduce, max, min, sum, findFirst, findAny, anyMatch, allMatch, etc., over imperative loops with continue and break statements
- Don't use functional programming if it hurts readability and adds cognitive complexity.
- Refer these Java files for Functional programming style guide for Java:
  - ds-algo/src/main/java/practice/ParallelCourses.java
  - ds-algo/src/main/java/practice/DetonateTheMaximumBombs.java
  - ds-algo/src/main/java/practice/SurroundedRegions.java
  - ds-algo/src/main/java/practice/FindTheSafestPathInGrid.java

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