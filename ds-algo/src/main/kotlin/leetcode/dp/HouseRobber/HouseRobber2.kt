package leetcode.dp.HouseRobber

/** https://leetcode.com/problems/house-robber-ii/ */
fun rob2(nums: IntArray): Int {
  if (nums.size == 1) {
    return nums[0]
  }
  return maxOf(robUtil(nums.slice(0..<nums.lastIndex)), robUtil(nums.slice(1..nums.lastIndex)))
}

private fun robUtil(nums: List<Int>): Int {
  var prev = 0
  var prevPrev = 0
  for (num in nums) {
    val tmp = prev
    prev = maxOf(prev, num + prevPrev)
    prevPrev = tmp
  }
  return prev
}
