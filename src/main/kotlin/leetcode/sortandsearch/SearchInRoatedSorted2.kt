/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

fun searchInRotatedSorted2(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.lastIndex): Boolean {
    if (left > right) {
        return false
    }
    val mid = (left + right) / 2
    if (nums[mid] == target) {
        return true
    }

    if (nums[left] < nums[mid]) {
        if (target >= nums[left] && target < nums[mid]) {
            return searchInRotatedSorted2(nums, target, left, mid - 1)
        }
        return searchInRotatedSorted2(
            nums,
            target,
            mid + 1,
            right
        ) // if above condition is not true, the element would have slipped through rotation to the right side. 
    }

    if (nums[left] > nums[mid]) {
        if (target <= nums[right] && target > nums[mid]) {
            return searchInRotatedSorted2(nums, target, mid + 1, right)
        }
        return searchInRotatedSorted2(nums, target, left, mid - 1)
    }

    if (nums[right] != nums[mid]) {
        return searchInRotatedSorted2(nums, target, mid + 1, right)
    }
    // search both sides
    val result = searchInRotatedSorted2(nums, target, left, mid - 1)
    return if (!result) searchInRotatedSorted2(
        nums,
        target,
        mid + 1,
        right
    ) else true
    
}