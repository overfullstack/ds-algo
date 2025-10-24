package leetcode.dp

/** https://leetcode.com/problems/house-robber-ii/ */
fun rob2(nums: IntArray): Int {
  if (nums.size == 1) {
    return nums[0]
  }
  return maxOf(robUtil(nums.slice(0..<nums.lastIndex)), robUtil(nums.slice(1..nums.lastIndex)))
}

private fun robUtil(nums: List<Int>): Int {
  var cur = 0
  var prev = 0
  for (num in nums) {
    val tmp = cur
    cur = maxOf(cur, num + prev)
    prev = tmp
  }
  return cur
}
