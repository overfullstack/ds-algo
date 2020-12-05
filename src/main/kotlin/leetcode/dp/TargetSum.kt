package leetcode.dp

fun findTargetSumWays(
    nums: IntArray,
    targetSum: Int,
    curSum: Int = 0,
    index: Int = 0,
    cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
): Int =
    when {
        (index to curSum) in cache -> cache[index to curSum]!!
        index == nums.size -> if (curSum == targetSum) 1 else 0
        else -> (findTargetSumWays(nums, targetSum, curSum + nums[index], index + 1, cache) +
                findTargetSumWays(nums, targetSum, curSum - nums[index], index + 1, cache))
                .also { cache[index to curSum] = it }
    }
