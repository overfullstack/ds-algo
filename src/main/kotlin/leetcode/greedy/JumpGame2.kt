package leetcode.greedy

/**
 * https://leetcode.com/problems/jump-game-ii/
 * Min Jumps to reach maxReachFromPrevPos
 */
fun jump(nums: IntArray): Int {
    var maxReachFromPrevPos = 0
    var reach = 0
    var jumps = 0
    for (i in 0 until nums.lastIndex) { // * Skipping lastIndex
        reach = maxOf(reach, i + nums[i])
        // This is like implicit BFS
        if (i == maxReachFromPrevPos) { // `maxReachFromPrevPos` updates to reach at current index
            jumps++
            maxReachFromPrevPos = reach
            if (maxReachFromPrevPos >= nums.lastIndex) {
                return jumps
            }
        }
    }
    return jumps
}
