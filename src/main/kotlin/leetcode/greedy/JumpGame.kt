package leetcode.greedy

/**
 * https://leetcode.com/problems/jump-game/
 */
fun canJump(nums: IntArray): Boolean {
    var reach = nums[0] // ! This is to handle `intArrayOf(0)` test case
    return (1..nums.lastIndex)
        .all { index -> (reach >= index).also { reach = maxOf(reach, index + nums[index]) } }
        // * `maxOf`, as reach might already be more that what the current num can addup
}
