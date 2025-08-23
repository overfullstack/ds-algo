package leetcode.slidingwindow

/** https://leetcode.com/problems/subarray-sum-equals-k/ We may have negative numbers. */
fun longestSubArraySumK(nums: IntArray, k: Int): Int {
  var maxLen = 0
  var sum = 0
  val map = mutableMapOf(0 to -1) // ! It is `-1` and NOT `0`, coz index 0 is also counted for sum
  for (i in nums.indices) {
    sum += nums[i]
    // * `putIfAbsent` instead of `merge`, coz we are looking for the longest array.
    // * So earlier the longer. So don't override the earliest index.
    map.putIfAbsent(sum, i)
    map[sum - k]?.let { maxLen = maxOf(maxLen, i - it) }
  }
  return maxLen
}
