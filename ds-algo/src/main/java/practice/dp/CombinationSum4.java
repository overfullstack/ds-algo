package practice.dp;

/* 29 Aug 2025 20:38 */

/** [377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/) */
public class CombinationSum4 {
	public int combinationSum4(int[] nums, int target) {
		var dp = new int[target + 1];
		dp[0] = 1;
		for (var sum = 1; sum <= target; sum++) {
			for (var num : nums) {
				// ! Borrow from all previous sums, to add up to combinations
				if (num <= sum) {
					dp[sum] += dp[sum - num];
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
