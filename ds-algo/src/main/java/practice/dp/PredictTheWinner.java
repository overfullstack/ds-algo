package practice.dp;

/* 28 Aug 2025 20:32 */

/** [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner/) */
public class PredictTheWinner {
	public boolean predictTheWinner(int[] nums) {
		// ! '=' coz the problem says with equal also player 1 wins
		return scoreDiff(0, nums.length - 1, nums, new int[nums.length][nums.length]) >= 0;
	}

	private static int scoreDiff(int left, int right, int[] nums, int[][] memo) {
		if (left == right) {
			return nums[left];
		}
		if (memo[left][right] != 0) {
			return memo[left][right];
		}
		var chooseLeft = nums[left] - scoreDiff(left + 1, right, nums, memo);
		var chooseRight = nums[right] - scoreDiff(left, right - 1, nums, memo);
		var scoreDiff = Math.max(chooseLeft, chooseRight);
		memo[left][right] = scoreDiff;
		return scoreDiff;
	}
}
