package practice;

import java.util.HashMap;

/* 22 Oct 2025 15:54 */

/** [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum) */
public class ContinuousSubarraySum {
	public boolean checkSubarraySum(int[] nums, int k) {
		var map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		var sum = 0;
		for (var i = 0; i < nums.length; i++) {
			sum += nums[i];
			sum %= k; // ! Check if same remainder occurred
			var prevIdx = map.get(sum);
			if (prevIdx != null) {
				if (i - prevIdx >= 2) { // ! problem specific: length is at least two
					return true;
				}
			} else { // ! Not overriding `prevIdx`, to maximize distance to satisfy `length >= 2`
				map.put(sum, i);
			}
		}
		return false;
	}

	static void main() {
		var continuousSubarraySum = new ContinuousSubarraySum();
		System.out.println(
				continuousSubarraySum.checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6)); // true
		System.out.println(
				continuousSubarraySum.checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6)); // true
		System.out.println(
				continuousSubarraySum.checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 13)); // false
	}
}
