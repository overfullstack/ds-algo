package practice.binarysearch;

/* 20 Oct 2025 13:03 */

import static java.lang.IO.println;

/**
 * [540. Single Element in a Sorted
 * Array](https://leetcode.com/problems/single-element-in-a-sorted-array/)
 */
public class SingleElementInASortedArray {
	// ! This can be achieved with rightMost search also, but leftMost is more intuitive
	public int singleNonDuplicate(int[] nums) {
		var left = 0;
		var right = nums.length - 1;
		while (left < right) {
			final var mid = left + (right - left) / 2; // ! Mid is left inclined
			final var isEvenIndex = mid % 2 == 0;
			final var pairMatchesRight = isEvenIndex && nums[mid] == nums[mid + 1];
			final var pairMatchesLeft = !isEvenIndex && nums[mid] == nums[mid - 1];
			final var pairsIntactOnLeftHalf = pairMatchesRight || pairMatchesLeft;
			if (!pairsIntactOnLeftHalf) { // ! Pairs NOT intact on left half (including mid)
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return nums[right];
	}

	static void main() {
		final var singleElementInASortedArray = new SingleElementInASortedArray();
		println(
				singleElementInASortedArray.singleNonDuplicate(new int[] {1, 1, 2, 3, 3, 4, 4, 8, 8})); // 2
		println(
				singleElementInASortedArray.singleNonDuplicate(new int[] {3, 3, 7, 7, 10, 11, 11})); // 10
	}
}
