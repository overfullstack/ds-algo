package leetcode.array.slidingwindow

/**
 * [862. Shortest Subarray with Sum at Least
 * K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k) Does contain negative
 * numbers.
 */
fun shortestSubarrayWithSumAtLeastK(nums: IntArray, k: Int): Int {
  val sumTill = nums.runningReduce(Int::plus)
  val dq = ArrayDeque<Int>() // ! Stores excluding starting points
  var minWindow = Int.MAX_VALUE
  for (i in sumTill.indices) {
    // ! Shrinking the window from start.
    // ! `sumTill[i] - sumTill[dq.first()]` = sum of `nums[dq.first()+1..i]`, excluding `dq.first()`
    while (dq.isNotEmpty() && sumTill[i] - sumTill[dq.first()] >= k) {
      // ! No `+1` as we exclude the `dq.first()` as sum is made from its next index
      minWindow = minOf(minWindow, i - dq.removeFirst())
    }
    // ! Exclude `-ve` indexes from the window
    while (dq.isNotEmpty() && sumTill[i] <= sumTill[dq.last()]) {
      dq.removeLast() // ! The negative index now becomes the first excluding starting position
    }
    dq.add(i)
  }
  return if (minWindow == Int.MAX_VALUE) -1 else minWindow
}

fun main() {
  println(shortestSubarrayWithSumAtLeastK(intArrayOf(2, -1, 2, 1), 3)) // 2
}
