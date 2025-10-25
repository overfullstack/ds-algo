package practice.binarysearch;

/* 25 Oct 2025 16:52 */

import java.util.Arrays;

public class TwoSumLessThanTarget {
	public int twoSumLessThanTarget(int[] nums, int target) {
		Arrays.sort(nums); // ! sort
		var result = Integer.MIN_VALUE; // ! Can't have `-1` for negative numbers case
		for (var i = 0; i < nums.length; i++) {
			var value = target - nums[i];
			var j = rightmost(value, i + 1, nums);
			if (j > i) {
				result = Math.max(result, nums[i] + nums[j]);
			}
		}
		return result == Integer.MIN_VALUE ? -1 : result;
	}

	private static int rightmost(int value, int left, int[] nums) {
		var right = nums.length - 1;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (value > nums[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	static void main() {
		var l = new TwoSumLessThanTarget();
		System.out.println(l.twoSumLessThanTarget(new int[] {-2, -3}, -4)); // -5
		System.out.println(l.twoSumLessThanTarget(new int[] {1, 1, 1, 1, 1}, 2)); // -1
		System.out.println(l.twoSumLessThanTarget(new int[] {2, 7, 11, 15}, 24)); // 22
		System.out.println(l.twoSumLessThanTarget(new int[] {3, 5, 1, 9, 7}, 3)); // -1
		System.out.println(l.twoSumLessThanTarget(new int[] {34, 23, 1, 24, 75, 33, 54, 8}, 60)); // 58
		System.out.println(l.twoSumLessThanTarget(new int[] {10, 20, 30}, 15)); // -1
	}
}
