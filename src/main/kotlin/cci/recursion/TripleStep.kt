package cci.recursion

// Initialized with first 3 fibnocci numbers
tailrec fun possibleWaysToRunUpStairs(stairCount: Int, a: Int = 1, b: Int = 1, c: Int = 2): Int =
    when (stairCount) {
        0 -> a
        1 -> b
        2 -> c
        else -> possibleWaysToRunUpStairs(stairCount - 1, b, c, a + b + c)
    }
