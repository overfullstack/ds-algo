package cci.array

fun isPalindromePermutation(str: String): Boolean =
  str.groupingBy { it }.eachCount().filterValues { it % 2 != 0 }.size <= 1
