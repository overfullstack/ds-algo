package practice;

/* 29 Aug 2025 20:38 */

/** [377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/) */
public class CombinationSum4 {
	public int combinationSum4(int[] nums, int target) {
		var dp = new int[target + 1];
		dp[0] = 1;
		for (var i = 1; i <= target; i++) {
			// !!! Layers multiple nums for same target
			// ! we consider all possible numbers that could be the "last" number in the sequence
			// ! Unlike Combination Sum 2
			for (var num : nums) {
				if (i >= num) {
					dp[i] += dp[i - num];
				}
			}
		}
		return dp[target];
	}

	static void main() {
		var combinationSum4 = new CombinationSum4();
		System.out.println(combinationSum4.combinationSum4(new int[] {1, 2, 3}, 4)); // 7
	}
}
