package leetcode.slidingwindow

/* 07 Aug 2025 08:19 */

/** [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) */
fun subarraySum(nums: IntArray, k: Int): Int {
  // ! For `k = 0`, `sum(0)..sum-k(0)` should be 1
  val map = mutableMapOf(0 to 1)
  var sum = 0
  var count = 0
  for (num in nums) {
    sum += num
    // ! `sum..sum-k` makes the same count as number of times `sum` has occurred
    map[sum - k]?.let { count += it }
    // ! Store number of times `sum` has occurred
    map.merge(sum, 1, Int::plus)
  }
  return count
}

fun main() {
  println(subarraySum(intArrayOf(1, 1, 1), 2)) // 2
  println(subarraySum(intArrayOf(1, 2, 3), 3)) // 2
  println(subarraySum(intArrayOf(1, -1, 0), 0)) // 3
  println(subarraySum(intArrayOf(1), 0)) // 0
}
