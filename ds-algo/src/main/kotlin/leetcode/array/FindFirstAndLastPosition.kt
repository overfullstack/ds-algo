package leetcode.array

import utils.toIntArray

/* 11 Aug 2025 13:37 */

fun searchRange(nums: IntArray, target: Int): IntArray = searchRange2(nums, target).toIntArray()

fun searchRange2(nums: IntArray, target: Int): Pair<Int, Int> {
  val firstPos = findFirst(nums, target)
  val lastPos = findLast(nums, target)
  return firstPos to lastPos
}

tailrec fun findFirst(
  nums: IntArray,
  target: Int,
  left: Int = 0,
  right: Int = nums.lastIndex,
): Int {
  if (left > right) return -1

  val mid = left + (right - left) / 2
  return when {
    nums[mid] == target && (mid == 0 || nums[mid - 1] != target) -> mid
    nums[mid] >= target -> findFirst(nums, target, left, mid - 1)
    else -> findFirst(nums, target, mid + 1, right)
  }
}

tailrec fun findLast(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.lastIndex): Int {
  if (left > right) return -1

  val mid = left + (right - left) / 2
  return when {
    nums[mid] == target && (mid == nums.lastIndex || nums[mid + 1] != target) -> mid
    nums[mid] <= target -> findLast(nums, target, mid + 1, right)
    else -> findLast(nums, target, left, mid - 1)
  }
}

fun main() {
  println(searchRange2(intArrayOf(5, 7, 7, 8, 8, 10), 8))
  println(searchRange2(intArrayOf(5, 7, 7, 8, 8, 10), 6))
}
