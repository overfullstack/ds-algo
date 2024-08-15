package leetcode.array.slidingwindow

/** https://leetcode.com/problems/minimum-size-subarray-sum/ NO negative numbers */
fun shortestSubArrayLenWithSumGTK(s: Int, nums: IntArray): Int {
  var minWindow = Int.MAX_VALUE
  var start = 0
  var targetSum = s
  for ((i, num) in nums.withIndex()) {
    targetSum -= num
    while (targetSum <= 0) {
      minWindow = minOf(minWindow, i - start + 1)
      targetSum += nums[start]
      start++
    }
  }
  return if (minWindow == Int.MAX_VALUE) 0 else minWindow
}
