package leetcode.greedy

/** https://leetcode.com/problems/jump-game/ */
fun canJump(nums: IntArray): Boolean {
  var reachFromPrevIndex = nums[0] // ! This is to handle `intArrayOf(0)` test case
  for (curIndex in 1..nums.lastIndex) {
    if (reachFromPrevIndex < curIndex) return false
    // * `maxOf`, as reach might already be more that what the current num can addup
    reachFromPrevIndex = maxOf(reachFromPrevIndex, curIndex + nums[curIndex])
    if (reachFromPrevIndex >= nums.lastIndex) return true
  }
  return true
}

fun main() {
  println(canJump(intArrayOf(5, 3, 1, 1, 4)))
}
