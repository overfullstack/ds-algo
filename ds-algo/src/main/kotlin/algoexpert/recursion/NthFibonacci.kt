package algoexpert.recursion

tailrec fun nthFibonacci(n: Int, index: Int = 2, f0: Int = 0, f1: Int = 1): Int =
  when (n) {
    1 -> f0
    2 -> f1
    index -> f1
    else -> nthFibonacci(n, index + 1, f1, f0 + f1)
  }
