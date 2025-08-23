package leetcode.slidingwindow

/**
 * https://leetcode.com/problems/contiguous-array/ We need to find longest contiguous subarray with
 * equal number of 0s and 1s
 */
fun findMaxLength(nums: IntArray): Int {
  var max = 0
  val countMap = mutableMapOf(0 to -1)
  var count = 0
  for (i in nums.indices) {
    if (nums[i] == 1) count++ else count--
    // If you encountered equal no.of 0s or 1s, the count is brought back to a value in the past.
    // ! No `+1` as `it` is excluded, as sum is made starting from its next index
    countMap[count]?.let { max = maxOf(max, i - it) }
    countMap.putIfAbsent(count, i) // ! Retain the earliest index to measure the longest
  }
  return max
}
