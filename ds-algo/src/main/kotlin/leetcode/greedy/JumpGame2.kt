package leetcode.greedy

/** [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/) */
fun minJumps(nums: IntArray): Int {
  if (nums.size == 1) return 0
  var currentEnd = 0
  var farthest = 0
  var jumps = 0
  for (i in 0 until nums.lastIndex) {
    farthest = maxOf(farthest, i + nums[i])
    if (i == currentEnd) {
      jumps++
      currentEnd = farthest
      if (currentEnd >= nums.lastIndex) {
        return jumps
      }
    }
  }
  return jumps
}

fun minJumps2(nums: IntArray): Int {
  var maxReachFromPrevPos = 0
  var reach = 0
  var jumps = 0
  for (i in 0 until nums.lastIndex) { // ! Skip lastIndex
    reach = maxOf(reach, i + nums[i])
    // * Skim through reach range, find the nextMaxReachIndex
    // * This is like implicit BFS
    if (i == maxReachFromPrevPos) {
      jumps++
      maxReachFromPrevPos = reach
      if (maxReachFromPrevPos >= nums.lastIndex) {
        return jumps
      }
    }
  }
  return jumps
}
