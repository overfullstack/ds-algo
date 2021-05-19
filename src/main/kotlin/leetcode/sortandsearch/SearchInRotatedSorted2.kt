/* gakshintala created on 1/1/20 */
package leetcode.sortandsearch

fun searchInRotatedSorted2(
    nums: IntArray,
    target: Int,
    left: Int = 0,
    right: Int = nums.lastIndex
): Boolean {
    if (left > right) {
        return false
    }
    val mid = (left + right) / 2
    return when {
        nums[mid] == target || nums[left] == target || nums[right] == target -> true
        nums[left] < nums[mid] -> when { // If sorted on the left side
            target > nums[left] && target < nums[mid] -> searchInRotatedSorted2(
                nums,
                target,
                left,
                mid - 1
            )
            // if above condition is not true, the element would have slipped through rotation to the right side.
            else -> searchInRotatedSorted2(nums, target, mid + 1, right)
        }
        nums[left] > nums[mid] -> when { // If sorted on the right side, comparing with left.
            target < nums[right] && target > nums[mid] -> searchInRotatedSorted2(
                nums,
                target,
                mid + 1,
                right
            )
            else -> searchInRotatedSorted2(nums, target, left, mid - 1)
        }
        // Implicit `nums[left] == nums[mid]` 2 2 2 2 3 4, this means left half is filled with all duplicates.
        nums[right] != nums[mid] -> searchInRotatedSorted2(nums, target, mid + 1, right)

        // If mid equals both left and right (2 3 4 2 2 2),
        // search both side, as duplicates may flow through mid, you don't have a way to say which side is filled with duplicates.
        else -> searchInRotatedSorted2(nums, target, left, mid - 1) || searchInRotatedSorted2(
            nums,
            target,
            mid + 1,
            right
        )
        // The worst case - O(n), where all are repeated except the one we are searching.
    }
}
