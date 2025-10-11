package educative.twopointers

/* 14 Jul 2025 10:41 */

/** [75. Sort Colors](https://leetcode.com/problems/sort-colors/) */
fun sortColors(nums: IntArray): IntArray {
  var start = 0
  var end = nums.lastIndex
  var current = 0
  while (current <= end) {
    when (nums[current]) {
      0 -> {
        nums.swap(start, current)
        start++
        current++
      }
      1 -> current++
      2 -> {
        nums.swap(current, end)
        end--
      }
    }
  }
  return nums
}

private fun IntArray.swap(index1: Int, index2: Int) {
  if (index1 != index2 && this[index1] != this[index2]) {
    this[index1] = this[index2].also { this[index2] = this[index1] }
  }
}

fun main() {
  println(sortColors(intArrayOf(2, 0, 1)).joinToString(", "))
}
