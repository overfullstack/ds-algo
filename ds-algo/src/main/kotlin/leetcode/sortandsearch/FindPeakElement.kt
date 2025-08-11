package leetcode.sortandsearch

/* 11 Aug 2025 11:47 */

/** [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/) */
tailrec fun findPeakElement(nums: IntArray, left: Int = 1, right: Int = nums.lastIndex - 1): Int {
  when {
    nums.size == 1 -> return 0
    nums[0] > nums[1] -> return 0
    nums.last() > nums[nums.lastIndex - 1] -> return nums.lastIndex
    left > right -> throw IllegalArgumentException("Invalid input")
  }
  val mid = left + (right - left) / 2
  return when {
    (mid > 0 && nums[mid] > nums[mid - 1]) && (mid < nums.lastIndex && nums[mid] > nums[mid + 1]) ->
      mid
    (mid > 0 && nums[mid] < nums[mid - 1]) -> findPeakElement(nums, left, mid - 1)
    else -> findPeakElement(nums, mid + 1, right) // ! No duplicates as per problem
  }
}

fun main() {
  println(findPeakElement(intArrayOf(2, 1)))
  println(findPeakElement(intArrayOf(1, 2, 1)))
  println(findPeakElement(intArrayOf(1, 2, 3, 1)))
  println(findPeakElement(intArrayOf(1, 2, 1, 3, 5, 6, 4)))
}
