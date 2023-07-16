package leetcode.backtracking

/** https://leetcode.com/problems/partition-to-k-equal-sum-subsets/ */
fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
  val sum = nums.sum()
  val max = nums.maxOrNull() ?: Int.MIN_VALUE
  if (sum % k != 0 || sum / k < max) {
    return false
  }
  return canPartitionKSubsets(nums, k, sum / k)
}

private fun canPartitionKSubsets(
  nums: IntArray,
  k: Int,
  targetSum: Int,
  used: BooleanArray = BooleanArray(nums.size),
  curSum: Int = 0,
  startIndex: Int = 0
): Boolean =
  when {
    k == 0 -> true
    curSum > targetSum -> false
    curSum == targetSum ->
      canPartitionKSubsets(nums, k - 1, targetSum, used) // * Restart after targetSum is met.
    else ->
      (startIndex..nums.lastIndex)
        .filter { !used[it] }
        .any { unusedIndex ->
          used[unusedIndex] = true
          canPartitionKSubsets(
              nums,
              k,
              targetSum,
              used,
              curSum + nums[unusedIndex],
              unusedIndex + 1
            )
            .also { used[unusedIndex] = false }
        }
  }
