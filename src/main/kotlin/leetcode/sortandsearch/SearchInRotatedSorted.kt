/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

fun searchInRotatedSorted(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.lastIndex): Int {
    if (left > right) {
        return -1
    }
    val mid = (left + right) / 2
    if (nums[mid] == target) {
        return mid
    }

    if (nums[left] <= nums[mid]) {
        if (target >= nums[left] && target < nums[mid]) {
            return searchInRotatedSorted(nums, target, left, mid - 1)
        }
        return searchInRotatedSorted(nums, target, mid + 1, right)
    }

    if (target <= nums[right] && target > nums[mid]) {
        return searchInRotatedSorted(nums, target, mid + 1, right)
    }
    return searchInRotatedSorted(nums, target, left, mid - 1)
    
}