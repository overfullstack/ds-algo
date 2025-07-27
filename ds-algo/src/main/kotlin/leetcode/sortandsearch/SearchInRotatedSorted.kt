/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

/**
 * [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)
 *
 * A rotated array still has the rotated part sorted in ascending order only. You always find
 * one-side sorted. It's easy if a target is found on that side. Or problem as usual. 4 paths:
 * - Left is Sorted
 *     - target in left range
 *     - Right side has a rotation problem, deal with it recursively.
 * - Right is Sorted
 *     - target in right range
 *     - Left side has a rotation problem, deal with it recursively.
 */
tailrec fun search(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.lastIndex): Int {
  if (left > right) {
    return -1
  }
  val mid = (left + right) / 2
  return when {
    nums[mid] == target -> mid
    nums[left] == target -> left
    nums[right] == target -> right
    nums[left] < nums[mid] ->
      when { // If left sorted, search for the target in left range.
        target > nums[left] && target < nums[mid] -> search(nums, target, left, mid - 1)
        // If the above condition is not true, then the target has slipped through rotation
        // to the right side. Problem restarts from right side.
        else -> search(nums, target, mid + 1, right)
      }
    target < nums[right] && target > nums[mid] -> search(nums, target, mid + 1, right)
    // Problem restarts from left side.
    else -> search(nums, target, left, mid - 1)
  }
}
