/* gakshintala created on 1/29/20 */
package leetcode.sortandsearch

tailrec fun findMin(nums: IntArray, left: Int = 0, right: Int = nums.lastIndex): Int {
    if (nums[left] <= nums[right]) {
        return nums[left]
    }
    val mid = (left + right) / 2
    return when {
        nums[left] <= nums[mid] -> findMin(nums, mid + 1, right) // Search the non-sorted side.
        else -> findMin(nums, left, mid)
    }
}
