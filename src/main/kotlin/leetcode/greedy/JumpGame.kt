package leetcode.greedy

fun canJump(nums: IntArray): Boolean {
    var reach = nums[0] // ! This is to handle `intArrayOf(0)` test case
    return (1..nums.lastIndex)
        .all { index -> (reach >= index).also { reach = maxOf(reach, index + nums[index]) } }
}
