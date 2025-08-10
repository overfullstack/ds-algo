package educative.dp

/* 10 Aug 2025 19:33 */

fun longestSubsequence(nums: IntArray): Int {
  val dp = IntArray(nums.size) { 1 }
  for (i in 1..nums.lastIndex) {
    // ! For every index, comparing with all the previous indices
    for (j in 0 until i) {
      if (nums[j] < nums[i]) {
        dp[i] = maxOf(dp[i], dp[j] + 1)
      }
    }
  }
  return dp.max()
}
