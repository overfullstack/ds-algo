package leetcode.dp.HouseRobber

/**
 * https://leetcode.com/problems/house-robber
 */
fun rob1(nums: IntArray): Int {
  var prev = 0
  var prevPrev = 0

  for (num in nums) {
    val tmp = prev
    prev = maxOf(prev, num + prevPrev)
    prevPrev = tmp
  }

  return prev
}
