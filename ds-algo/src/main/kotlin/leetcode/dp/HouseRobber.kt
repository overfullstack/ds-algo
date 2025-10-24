package leetcode.dp

/** [198. House Robber](https://leetcode.com/problems/house-robber) */
fun rob1(nums: IntArray): Int {
  var cur = 0
  var prev = 0
  for (num in nums) {
    val tmp = cur
    cur = maxOf(cur, num + prev)
    prev = tmp
  }

  return cur
}
