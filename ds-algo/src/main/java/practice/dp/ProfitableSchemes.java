package practice.dp;

import java.util.Arrays;

/* 27 Sep 2025 18:00 */

/** [879. Profitable Schemes](https://leetcode.com/problems/profitable-schemes/) */
public class ProfitableSchemes {
  private static final int MOD = (int)1e9 + 7;
	public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
		var memo = new int[102][102][102];
		for (var memo2 : memo) { // ! Fill with `-1` as the problem states profit can hold 0 too
			for (var memo1 : memo2) {
				Arrays.fill(memo1, -1);
			}
		}
		return solve(0, 0, 0, n, minProfit, group, profit, memo);
	}

	private int solve(
			int idx,
			int curGroup,
			int curProfit,
			int n,
			int minProfit,
			int[] group,
			int[] profit,
			int[][][] memo) {
		if (idx == profit.length) {
			return (curGroup <= n && curProfit >= minProfit) ? 1 : 0;
		}
		if (curGroup > n) {
			return 0;
		}
		if (memo[idx][curGroup][curProfit] != -1) {
			return memo[idx][curGroup][curProfit];
		}

		var excludeGroup = solve(idx + 1, curGroup, curProfit, n, minProfit, group, profit, memo);
		// ! Math.min to keep the memo index under `102` and anyway we only care about the minProfit
		var includeGroup =
				solve(
						idx + 1,
						curGroup + group[idx],
						Math.min(minProfit, curProfit + profit[idx]),
						n,
						minProfit,
						group,
						profit,
						memo);
		memo[idx][curGroup][curProfit] = (excludeGroup + includeGroup) % MOD;
    return memo[idx][curGroup][curProfit];
	}

	static void main() {
		var obj = new ProfitableSchemes();
		System.out.println(obj.profitableSchemes(5, 3, new int[] {2, 2}, new int[] {2, 3})); // 2
		System.out.println(obj.profitableSchemes(10, 5, new int[] {2, 3, 5}, new int[] {6, 7, 8})); // 7
	}
}
