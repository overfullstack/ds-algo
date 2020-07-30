package leetcode.backtracking

fun permute(
    nums: IntArray,
    permutation: List<Int> = emptyList(),
    used: BooleanArray = BooleanArray(nums.size)
): List<List<Int>> =
    if (permutation.size == nums.size) {
        listOf(permutation)
    } else {
        nums.indices.filter { !used[it] }.flatMap { unusedIndex ->
            used[unusedIndex] = true
            permute(nums, permutation + nums[unusedIndex], used).also { used[unusedIndex] = false }
        }
    }
