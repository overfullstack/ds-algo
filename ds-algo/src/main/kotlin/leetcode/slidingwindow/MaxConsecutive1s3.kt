package leetcode.slidingwindow

/**
 * [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) We may
 * change up to K values from 0 to 1
 */
fun longestOnes(nums: IntArray, k: Int): Int {
  var zeroCount = 0
  var start = 0
  var maxWindow = 0
  for ((i, num) in nums.withIndex()) {
    if (num == 0) {
      zeroCount++
    }
    if (zeroCount > k) {
      if (nums[start] == 0) zeroCount--
      start++ // ! Shrink window, it doesn't matter if it's 0 or not as window length remains same
    } else maxWindow = i - start + 1 // ! Either constant or increasing
  }
  return maxWindow
}

fun main() {
  println(longestOnes(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2))
  println(longestOnes(intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1), 3))
  println(longestOnes(intArrayOf(0, 0, 0, 0), 0))
}
