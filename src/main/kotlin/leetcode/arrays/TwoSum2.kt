package leetcode.arrays

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
 */
tailrec fun twoSum(nums: IntArray, target: Int, result: Pair<Int, Int> = 0 to nums.lastIndex): IntArray =
    when {
        result.first < 0 || result.second > nums.lastIndex -> intArrayOf()
        nums[result.first] + nums[result.second] < target -> twoSum(nums, target, result.first + 1 to result.second)
        nums[result.first] + nums[result.second] > target -> twoSum(nums, target, result.first to result.second - 1)
        else -> intArrayOf(result.first + 1, result.second + 1) // +1 is problem specific
    }
// If problem asks for all unique pairs, after finding one, increment and decrement first and second at the same time.
// To skip duplicates increment/decrement untill previous is same as current.
