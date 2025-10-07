package practice.binarysearch;

/* 03 Sep 2025 08:25 */

import java.util.Arrays;

/** [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) */
public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int k) {
		var left = Arrays.stream(nums).max().orElse(0);
		var right = Arrays.stream(nums).sum();
		// ! Minimized Maximum = Leftmost
		while (left < right) {
			var partitionSum = left + (right - left) / 2;
			var partitionCount = partitionCount(nums, partitionSum);
			if (partitionCount <= k) {
				right = partitionSum;
			} else {
				left = partitionSum + 1;
			}
		}
		return right;
	}

	private static int partitionCount(int[] nums, int capacity) {
		var partitionCount = 0;
		var curSum = 0;
		for (var num : nums) {
			curSum += num;
			if (curSum > capacity) {
				partitionCount++;
				curSum = num;
			}
		}
		return partitionCount + 1;
	}

	static void main() {
		var sol = new SplitArrayLargestSum();
		System.out.println(sol.splitArray(new int[] {7, 2, 5, 10, 8}, 2)); // 18
		System.out.println(sol.splitArray(new int[] {1, 2, 3, 4, 5}, 2)); // 9
	}
}
