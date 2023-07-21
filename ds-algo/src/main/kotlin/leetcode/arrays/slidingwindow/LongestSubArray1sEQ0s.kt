package leetcode.arrays.slidingwindow

/**
 * https://leetcode.com/problems/contiguous-array/ We need to find longest contiguous subarray with
 * equal number of 0s and 1s
 */
fun longestContiguousSubArray(nums: IntArray): Int {
  var max = 0
  val countMap = mutableMapOf(0 to -1)
  var count = 0
  for (i in nums.indices) {
    if (nums[i] == 1) count++ else count--
    // If you encountered equal no.of 0s or 1s, the count is brought back to a value in the past.
    countMap.merge(count, i) { lastOccurance, curOccurance ->
      max = maxOf(max, curOccurance - lastOccurance)
      lastOccurance
    }
  }
  return max
}
