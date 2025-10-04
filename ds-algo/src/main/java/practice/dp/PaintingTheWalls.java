package practice.dp;

import static java.lang.IO.println;

import java.util.Arrays;

/* 02 Oct 2025 00:07 */

/** [2742. Painting the Walls](https://leetcode.com/problems/painting-the-walls/) */
public class PaintingTheWalls {
	public int paintWalls(int[] cost, int[] time) {
		var memo = new int[cost.length + 1][cost.length + 1]; // allow idx == n and walls 0..n
		for (var m : memo) {
			Arrays.fill(m, -1);
		}
		return paintWalls(0, cost.length, cost, time, memo);
	}

	private static int paintWalls(int idx, int remainingWalls, int[] cost, int[] time, int[][] memo) {
		if (remainingWalls <= 0) {
			return 0;
		}
		if (idx == cost.length) { // ! `remainingWalls` present but we reached end
			return Integer.MAX_VALUE;
		}
		if (memo[idx][remainingWalls] != -1) {
			return memo[idx][remainingWalls];
		}
		var next = paintWalls(idx + 1, remainingWalls - (time[idx] + 1), cost, time, memo);
		var paint = next == Integer.MAX_VALUE ? Integer.MAX_VALUE : cost[idx] + next;
		var dontPaint = paintWalls(idx + 1, remainingWalls, cost, time, memo);
		memo[idx][remainingWalls] = Math.min(paint, dontPaint);
		return memo[idx][remainingWalls];
	}

	static void main() {
		var sol = new PaintingTheWalls();
		println(sol.paintWalls(new int[] {2, 3, 4, 2}, new int[] {1, 1, 1, 1})); // 4
		println(sol.paintWalls(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4})); // 3
	}
}
