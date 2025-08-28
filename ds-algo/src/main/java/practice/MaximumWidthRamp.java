package practice;

import java.util.Stack;

/* 28 Aug 2025 17:52 */

/** [962. Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp/) */
public class MaximumWidthRamp {
	public int maxWidthRamp(int[] nums) {
		var stk = new Stack<Integer>();
		for (var i = 0; i < nums.length; i++) {
			if (stk.isEmpty() || nums[i] < nums[stk.peek()]) {
				stk.add(i);
			}
		}
		var maxRampWidth = 0;
		for (var i = nums.length - 1; i >= 0; i--) {
			while (!stk.isEmpty() && nums[i] >= nums[stk.peek()]) { // `=` as per problem
				maxRampWidth = Math.max(maxRampWidth, i - stk.pop());
			}
		}
		return maxRampWidth;
	}

	static void main() {
		var maxWidthRamp = new MaximumWidthRamp();
		System.out.println(maxWidthRamp.maxWidthRamp(new int[] {6, 0, 8, 2, 1, 5}));
		System.out.println(maxWidthRamp.maxWidthRamp(new int[] {9, 8, 1, 0, 1, 9, 4, 0, 4, 1})); // 7
	}
}
