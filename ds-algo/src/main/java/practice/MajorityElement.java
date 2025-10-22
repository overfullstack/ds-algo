package practice;

import java.util.Arrays;

/* 22 Oct 2025 11:06 */

/** [169. Majority Element](https://leetcode.com/problems/majority-element/) */
public class MajorityElement {
	public int majorityElement(int[] nums) {
		var votes = 1; // ! 1 for nums[0]
		var curNum = nums[0];
		for (var num : nums) {
			if (num == curNum) {
				votes++;
			} else {
				votes--;
			}
			if (votes == 0) {
				curNum = num;
				votes = 1;
			}
		}
		return isNumMajority(curNum, nums) ? curNum : -1;
	}

	// ! This is not needed for this leetcode problem
	// ! but it handles case where there is no majority element
	private static boolean isNumMajority(int num, int[] nums) {
		final var freq = Arrays.stream(nums).filter(n -> n == num).count();
		return freq > nums.length / 2;
	}
}
