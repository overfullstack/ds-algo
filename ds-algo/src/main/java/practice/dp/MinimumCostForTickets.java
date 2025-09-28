package practice.dp;

/* 28 Sep 2025 16:51 */

/** [983. Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/) */
public class MinimumCostForTickets {
	public int mincostTickets(int[] days, int[] costs) {
		var memo = new int[days.length + 1]; // Cost starts with `1` as per problem, so no `-1` init
		return solve(0, days, costs, memo);
	}

	private static int solve(int idx, int[] days, int[] costs, int[] memo) {
		if (idx == days.length) {
			return 0;
		}
		if (memo[idx] != 0) {
			return memo[idx];
		}
		var dayTicket = costs[0] + solve(idx + 1, days, costs, memo);
		var nextWeekIdx = idx;
		while (nextWeekIdx < days.length && days[nextWeekIdx] < days[idx] + 7) {
			nextWeekIdx++;
		}
		var weekTicket = costs[1] + solve(nextWeekIdx, days, costs, memo);
		var nextMonthIdx = idx;
		while (nextMonthIdx < days.length && days[nextMonthIdx] < days[idx] + 30) {
			nextMonthIdx++;
		}
		var monthTicket = costs[2] + solve(nextMonthIdx, days, costs, memo);
		memo[idx] = Math.min(dayTicket, Math.min(weekTicket, monthTicket));
		return memo[idx];
	}

	static void main() {
		var obj = new MinimumCostForTickets();
		System.out.println(
				obj.mincostTickets(new int[] {1, 4, 6, 7, 8, 20}, new int[] {2, 7, 15})); // 11
		System.out.println(
				obj.mincostTickets(
						new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[] {2, 7, 15})); // 17
	}
}
