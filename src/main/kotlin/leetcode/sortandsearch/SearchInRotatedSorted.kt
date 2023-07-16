/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

/**
 * 4 paths Left is Sorted
 * - target in left range
 * - target in right range Right is Sorted
 * - target in right range
 * - target in left range
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
      when { // If left sorted
        target > nums[left] && target < nums[mid] ->
          search(nums, target, left, mid - 1) // Is target in left range.
        // If above condition is not true, then the target has slipped through rotation to the right
        // side.
        else -> search(nums, target, mid + 1, right)
      }
    target < nums[right] && target > nums[mid] -> search(nums, target, mid + 1, right)
    else -> search(nums, target, left, mid - 1)
  // A rotated array still have the rotated part sorted in ascending order only.
  }
}
