package practice.dp;

import java.util.Arrays;

/* 01 Oct 2025 21:19 */

/** [3823 · Maximum Profit From Trading Stocks](https://www.lintcode.com/problem/3823/) */
public class MaximumProfitFromTradingStocks {
	public int maximumProfit(int[] present, int[] future, int budget) {
		var memo = new int[present.length][budget + 1];
		for (var m : memo) {
			Arrays.fill(m, -1);
		}
		return solve(0, budget, present, future, memo);
	}

	private static int solve(
			int idx, int remainingBudget, int[] present, int[] future, int[][] memo) {
		if (idx == present.length) {
			return 0;
		}
		if (memo[idx][remainingBudget] != -1) {
			return memo[idx][remainingBudget];
		}
		var exclude = solve(idx + 1, remainingBudget, present, future, memo);
		var include =
				remainingBudget - present[idx] >= 0
						? future[idx]
								- present[idx]
								+ solve(idx + 1, remainingBudget - present[idx], present, future, memo)
						: Integer.MIN_VALUE;
		memo[idx][remainingBudget] = Math.max(exclude, include);
		return memo[idx][remainingBudget];
	}

	static void main() {
		var sol = new MaximumProfitFromTradingStocks();
		System.out.println(
				sol.maximumProfit(new int[] {5, 4, 6, 2, 3}, new int[] {8, 5, 4, 3, 5}, 10)); // 6
		System.out.println(sol.maximumProfit(new int[] {2, 2, 5}, new int[] {3, 4, 10}, 6)); // 5
		System.out.println(sol.maximumProfit(new int[] {3, 3, 12}, new int[] {0, 3, 15}, 10)); // 0
	}
}
