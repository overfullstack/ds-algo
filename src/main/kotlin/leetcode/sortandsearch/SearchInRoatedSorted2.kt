/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

fun searchInRotatedSorted2(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.lastIndex): Boolean {
    if (left > right) {
        return false
    }
    val mid = (left + right) / 2
    return when {
        nums[mid] == target || nums[left] == target || nums[right] == target -> true
        nums[left] < nums[mid] -> when { // If sorted on the left side
            target > nums[left] && target < nums[mid] -> searchInRotatedSorted2(nums, target, left, mid - 1)
            // if above condition is not true, the element would have slipped through rotation to the right side.
            else -> searchInRotatedSorted2(nums, target, mid + 1, right)
        }
        nums[left] > nums[mid] -> when { // If sorted on the right side, comparing with left, to state more declarative that if both cases fail `nums[left] != nums[mid]`
            target < nums[right] && target > nums[mid] -> searchInRotatedSorted2(nums, target, mid + 1, right)
            else -> searchInRotatedSorted2(nums, target, left, mid - 1)
        }
        // Implicit `nums[left] != nums[mid]`
        nums[right] != nums[mid] -> searchInRotatedSorted2(nums, target, mid + 1, right)
        
        // If mid equals both left and right, search both sides, if not found on left, search right.
        else -> searchInRotatedSorted2(nums, target, left, mid - 1)
                || searchInRotatedSorted2(nums, target, mid + 1, right) 
        // The worst case - O(n), where all are repeated except the one we are searching.
    }
}
