package practice;

import java.util.Arrays;

/* 24 Oct 2025 16:17 */

/** [3733 · Count Pairs in Two Arrays](https://www.lintcode.com/problem/3733/) */
public class CountPairsInTwoArrays {
	public long countPairs(int[] nums1, int[] nums2) {
		var diff = new int[nums1.length];
		for (var i = 0; i < nums1.length; i++) {
			diff[i] = nums1[i] - nums2[i];
		}
		Arrays.sort(diff);
		var result = 0L;
		var left = 0;
		var right = nums1.length - 1;
		while (left < right) {
			// ! (xl + xr) > (yl + yr) => (xl - yl) + (xr - yr) > 0 => (dl + dr) > 0
			while (left < right && diff[left] + diff[right] <= 0) {
				left++; // ! Increase left until the sum is positive
			}
			result += (right - left); // ! All pairs from left to right are valid
			right--; // ! Try with a different xr, yr pair
		}
		return result;
	}

	static void main() {
		var countPairsInTwoArrays = new CountPairsInTwoArrays();
		System.out.println(
				countPairsInTwoArrays.countPairs(new int[] {1, 1, 2, 2}, new int[] {2, 2, 1, 1})); // 1
		System.out.println(
				countPairsInTwoArrays.countPairs(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1})); // 2
	}
}
