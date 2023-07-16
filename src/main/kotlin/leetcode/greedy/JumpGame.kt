package leetcode.greedy

/** https://leetcode.com/problems/jump-game/ */
fun canJump(nums: IntArray): Boolean {
  var reachFromPrevIndex = nums[0] // ! This is to handle `intArrayOf(0)` test case
  return (1..nums.lastIndex).all { curIndex ->
    (reachFromPrevIndex >= curIndex).also {
      reachFromPrevIndex = maxOf(reachFromPrevIndex, curIndex + nums[curIndex])
    }
  }
  // * `maxOf`, as reach might already be more that what the current num can addup
}
