package practice.dp;

import java.util.Arrays;

/* 28 Sep 2025 07:50 */

/** [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) */
public class MinCostClimbingStairs {
	public int minCostClimbingStairs(int[] cost) {
		var memo = new int[cost.length];
		Arrays.fill(memo, -1);
		// ! Can start at either 0 or 1
		var startStair0 = solve(0, cost, memo);
		var startStair1 = solve(1, cost, memo); // ! memo is shared
		return Math.min(startStair0, startStair1);
	}

	private static int solve(int idx, int[] cost, int[] memo) {
		if (idx >= cost.length) {
			return 0;
		}
		if (memo[idx] != -1) {
			return memo[idx];
		}
		var oneStair = solve(idx + 1, cost, memo);
		var twoStairs = solve(idx + 2, cost, memo);
		// ! For problems with multiple idx + n
		// ! we accumulate cost after return instead of making it a function param
		memo[idx] = cost[idx] + Math.min(oneStair, twoStairs);
		return memo[idx];
	}

	static void main() {
		var obj = new MinCostClimbingStairs();
		System.out.println(obj.minCostClimbingStairs(new int[] {10, 15, 20})); // 15
		System.out.println(
				obj.minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
	}
}
