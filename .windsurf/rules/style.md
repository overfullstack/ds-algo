---
trigger: always_on
description: 
globs: 
---

- Use this codebase's functional Kotlin style: expression functions, sequences, immutable state, when expressions
- Reduce state mutation as much as possible. Prefer state transformation
- While generating Kotlin code, specify all variable types and functional return types explicitly

# Functional Programming Patterns
- Chain operations using sequences: prefer `.asSequence()` for multiple transformations
- Build immutable data flow: pass state through parameters, return new state instead of mutating
- Use functional combinators: map, filter, flatMap, fold, firstOrNull over loops
- Default parameters for recursive accumulators: use `= emptySet()`, `= emptyList()`
- Prefer single-expression functions that return directly from when/if expressions

# Kotlin-Specific Patterns
- Always use `when` expressions over if-else chains
- Use ranges: `downTo`, `until`, `in`, `indices` for iterations
- String operations: use `substring(range)` with IntRange, not separate indices
- Collection operations: use `+` operator for adding to immutable collections
- Prefer `?.let { }` over null checks
- Use `firstOrNull()` with elvis `?:` for fallback values

# Recursion Patterns
- For recursive functions with state, pass immutable state as parameters
- Return complete result from base case, not accumulate via side effects
- Use tailrec only when the recursive call is truly in tail position
- Cache/memoization: use MutableMap parameter with default empty map

# Code Structure
- Group related functions using nested functions when they share context
- Use data classes for grouping related immutable state
- Extension functions for domain-specific operations
- Inline functions for higher-order functions that are called frequently