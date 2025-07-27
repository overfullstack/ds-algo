/* gakshintala created on 1/29/20 */
package leetcode.sortandsearch

/**
 * [Find Minimum in Rotated Sorted
 * Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array)
 */
tailrec fun findMin(nums: IntArray, left: Int = 0, right: Int = nums.lastIndex): Int {
  // * All rotated elements are less than non-rotated elements.
  // * There is no way left goes into rotated part other than being on the point of rotation.
  // * As we always move right to mid if we find an anomaly
  if (nums[left] <= nums[right]) {
    return nums[left]
  }
  val mid = (left + right) / 2
  return when {
    // left to mid is sorted, search on the other non-sorted side.
    // ! Notice `<=` as the calculation of mid has left affinity
    nums[left] <= nums[mid] -> findMin(nums, mid + 1, right)
    else -> findMin(nums, left, mid)
  }
}
