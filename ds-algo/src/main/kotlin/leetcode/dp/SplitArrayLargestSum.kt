package leetcode.dp

/* 11 Aug 2025 17:38 */

fun splitArray(nums: IntArray, k: Int): Int {
  val dp = IntArray(nums.size + 1)
  for (i in 1..nums.lastIndex + 1) {
    dp[i] = nums[i - 1]
    var partitionSum = nums[i - 1]
    var j = 1
    while (j <= k && (i - j) >= 0) {
      partitionSum += nums[i - j]
      dp[i] = maxOf(dp[i], dp[i - j] + partitionSum)
      j++
    }
  }
  return dp.max()
}

fun main() {
  println(splitArray(intArrayOf(7, 2, 5, 10, 8), 2))
  println(splitArray(intArrayOf(1, 2, 3, 4, 5), 2))
}
