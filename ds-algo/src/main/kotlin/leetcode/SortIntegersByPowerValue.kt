package leetcode

import java.util.PriorityQueue

/* 04 Aug 2025 13:53 */

/**
 * [1387. Sort Integers by The Power
 * Value](https://leetcode.com/problems/sort-integers-by-the-power-value)
 */
fun getKth(lo: Int, hi: Int, k: Int): Int {
  val pq =
    PriorityQueue(
      Comparator.comparingInt<Pair<Int, Int>> { it.second }.thenComparingInt { it.first }
    )
  val cache = mutableMapOf<Int, Int>()
  (lo..hi).map { x -> x to powerValue(x, cache) }.forEach { pq.add(it) }
  repeat(k - 1) {
    pq.poll()
  }
  val result = pq.poll()
  return result.first
}

// ! We cannot have tailrec with memoization
fun powerValue(x: Int, cache: MutableMap<Int, Int>): Int {
  cache[x]?.let {
    return it
  }

  val power =
    when {
      x == 1 -> 0
      x.mod(2) == 0 -> powerValue(x / 2, cache) + 1
      else -> powerValue(3 * x + 1, cache) + 1
    }

  cache[x] = power
  return power
}

fun main() {
  println(getKth(12, 15, 2))
  println(getKth(7, 11, 4)) // 7
}
