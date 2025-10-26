package practice.binarysearch;

import java.util.Arrays;

/* 25 Oct 2025 13:25 */

/**
 * [2389. Longest Subsequence With Limited
 * Sum](https://leetcode.com/problems/longest-subsequence-with-limited-sum/)
 */
public class LongestSubsequenceWithLimitedSum {
	public int[] answerQueries(int[] nums, int[] queries) {
		Arrays.sort(nums); // ! Subsequence, so it's ok to lose order of elements
		var prefix = new int[nums.length];
		prefix[0] = nums[0];
		for (var i = 1; i < nums.length; i++) {
			prefix[i] = nums[i] + prefix[i - 1];
		}
		return Arrays.stream(queries).map(q -> rightMost(q, prefix) + 1).toArray();
	}

	// ! Sum should be <= query value => value >= prefix[mid]
	private static int rightMost(int value, int[] prefix) {
		var left = 0;
		var right = prefix.length - 1;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (value >= prefix[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return Math.max(right, 0);
	}

	static void main() {
		var l = new LongestSubsequenceWithLimitedSum();
		System.out.println(
				Arrays.stream(l.answerQueries(new int[] {4, 5, 2, 1}, new int[] {3, 10, 21}))
						.mapToObj(i -> i + " ")
						.toList()); // [2, 3, 4]
		System.out.println(
				Arrays.stream(l.answerQueries(new int[] {2, 3, 4, 5}, new int[] {1}))
						.mapToObj(i -> i + " ")
						.toList()); // [0]
	}
}
