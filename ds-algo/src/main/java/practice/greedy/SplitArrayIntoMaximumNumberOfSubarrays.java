package practice.greedy;

/* 18 Oct 2025 18:59 */

/**
 * [2871. Split Array Into Maximum Number of
 * Subarrays](https://leetcode.com/problems/split-array-into-maximum-number-of-subarrays)
 */
public class SplitArrayIntoMaximumNumberOfSubarrays {
	public int maxSubarrays(int[] nums) {
		var value = -1;
		var result = 0;
		for (var num : nums) {
			value &= num;
			if (value == 0) {
				value = -1;
				result++;
			}
		}
		return result == 0 ? 1 : result;
	}
}
