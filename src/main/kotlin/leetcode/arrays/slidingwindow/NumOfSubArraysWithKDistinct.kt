package leetcode.arrays.slidingwindow

/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 */
fun subarraysWithKDistinct(A: IntArray, K: Int): Int =
    subArraysWithAtMostKDistinct(A, K) - subArraysWithAtMostKDistinct(A, K - 1)

fun subArraysWithAtMostKDistinct(A: IntArray, K: Int): Int {
    var start = 0
    var windowLenWithAtMostK = 0
    val freqInWindow = mutableMapOf<Int, Int>()

    for ((index, value) in A.withIndex()) {
        freqInWindow.merge(value, 1) { freq, _ -> freq.inc() }
        while (freqInWindow.size > K) {
            freqInWindow.computeIfPresent(A[start]) { _, freq ->
                start++
                when (freq) {
                    1 -> null
                    else -> freq.dec()
                }
            }
        }
        windowLenWithAtMostK += (index - start + 1) // * This cumulates all sub arrays length
    }
    return windowLenWithAtMostK
}
