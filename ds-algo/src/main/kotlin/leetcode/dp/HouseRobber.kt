package leetcode.dp

/** [198. House Robber](https://leetcode.com/problems/house-robber) */
fun robBottomUp(nums: IntArray): Int {
  if (nums.size == 1) {
    return nums[0]
  }
  val dp = IntArray(nums.size) { -1 }
  dp[0] = nums[0]
  dp[1] = maxOf(nums[0], nums[1])
  for (i in 2..nums.lastIndex) {
    dp[i] = maxOf(dp[i - 1], nums[i] + dp[i - 2])
  }
  return dp.last()
}

fun robTopDown(nums: IntArray, idx: Int = 0, memo: IntArray = IntArray(nums.size) { -1 }): Int =
  when {
    idx >= nums.lastIndex + 1 -> 0
    memo[idx] != -1 -> memo[idx]
    else -> {
      val include = nums[idx] + robTopDown(nums, idx + 2, memo)
      val exclude = robTopDown(nums, idx + 1, memo)
      maxOf(include, exclude).also { memo[idx] = it }
    }
  }

fun rob(nums: IntArray): Int {
  var prev = 0
  var prevPrev = 0
  for (num in nums) {
    val tmp = prev
    prev = maxOf(prev, num + prevPrev)
    prevPrev = tmp
  }
  return prev
}

fun main() {
  println(robBottomUp(intArrayOf(1, 2, 3, 1))) // 4
  println(robBottomUp(intArrayOf(2, 7, 9, 3, 1))) // 12

  println(robTopDown(intArrayOf(1, 2, 3, 1))) // 4
  println(robTopDown(intArrayOf(2, 7, 9, 3, 1))) // 12
}
