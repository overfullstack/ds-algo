package leetcode.slidingwindow

/**
 * [862. Shortest Subarray with Sum at Least
 * K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k) Does contain negative
 * numbers.
 */
fun shortestSubarrayWithSumAtLeastK(nums: IntArray, k: Int): Int {
  val sumTill = nums.runningFold(0, Int::plus)
  val dq = ArrayDeque<Int>() // ! Stores indices in prefix sum array
  var minWindow = Int.MAX_VALUE
  for (i in sumTill.indices) {
    // ! Shrinking the window from start.
    // ! `sumTill[i] - sumTill[dq.first()]` = sum of subarray from `dq.first()+1` to `i` in original array
    while (dq.isNotEmpty() && sumTill[i] - sumTill[dq.first()] >= k) {
      minWindow = minOf(minWindow, i - dq.removeFirst())
    }
    // ! Maintain monotonic deque: remove indices with greater or equal prefix sums
    while (dq.isNotEmpty() && sumTill[i] <= sumTill[dq.last()]) {
      dq.removeLast()
    }
    dq.add(i)
  }
  return if (minWindow == Int.MAX_VALUE) -1 else minWindow
}

fun main() {
  println(shortestSubarrayWithSumAtLeastK(intArrayOf(2, -1, 2, 1), 3)) // 2
}
