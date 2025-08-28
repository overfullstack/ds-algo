package leetcode.slidingwindow

/** https://leetcode.com/problems/subarrays-with-k-different-integers/ */
fun subarraysWithKDistinct(A: IntArray, K: Int): Int =
  subArraysWithAtMostKDistinct(A, K) - subArraysWithAtMostKDistinct(A, K - 1)

fun subArraysWithAtMostKDistinct(a: IntArray, k: Int): Int {
  var start = 0
  var windowLenWithAtMostK = 0
  val freqInWindow = mutableMapOf<Int, Int>()

  for ((index, value) in a.withIndex()) {
    freqInWindow.merge(value, 1, Int::plus)
    while (freqInWindow.size > k) {
      freqInWindow.computeIfPresent(a[start]) { _, freq ->
        start++
        if (freq == 1) null else freq.dec()
      }
    }
    windowLenWithAtMostK += (index - start + 1) // * This cumulates all sub array length
  }
  return windowLenWithAtMostK
}
