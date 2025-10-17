package leetcode.greedy

/** [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/) */
fun minJumps(nums: IntArray): Int {
  var curMaxReachIndex = 0
  var curIndex = 0
  var jumps = 0
  while (curIndex < nums.lastIndex) {
    val nextMaxReachIndex = (curIndex..curMaxReachIndex).maxOf { it + nums[it] }
    if (nextMaxReachIndex >= nums.lastIndex) {
      return jumps + 1
    }
    curIndex = curMaxReachIndex // ! Jump to curMaxReachIndex
    curMaxReachIndex = nextMaxReachIndex
    jumps++
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
