package educative.dp

/* 26 Jul 2025 22:18 */

fun maxSubArraySum(nums: IntArray): Int {
  var maxSoFar = nums[0]
  var maxEndingHere = nums[0]
  for (i in 1..nums.lastIndex) {
    maxEndingHere = maxOf(nums[i], maxEndingHere + nums[i])
    maxSoFar = maxOf(maxSoFar, maxEndingHere)
  }
  return maxSoFar
}
