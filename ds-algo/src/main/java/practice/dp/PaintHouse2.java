package practice.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

/* 14 Oct 2025 18:51 */

/** [516 · Paint House II](https://www.lintcode.com/problem/516/) */
public class PaintHouse2 {
	public int minCostII(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		for (var houseIdx = 1; houseIdx < costs.length; houseIdx++) {
			for (var colorIdx = 0; colorIdx < costs[0].length; colorIdx++) {
				final var hIdx = houseIdx;
				final var clrIdx = colorIdx;
				costs[houseIdx][colorIdx] +=
						IntStream.range(0, costs[0].length)
								.filter(cIdx -> cIdx != clrIdx)
								.map(cIdx -> costs[hIdx - 1][cIdx])
								.min()
								.orElseThrow();
			}
		}
		return Arrays.stream(costs[costs.length - 1]).min().orElseThrow();
	}

	public int minCostIITopDown(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		return solve(0, -1, costs, new int[costs.length][costs[0].length + 1]);
	}

	private static int solve(int idx, int prevColor, int[][] costs, int[][] memo) {
		if (idx == costs.length) {
			return 0;
		}
		if (memo[idx][prevColor + 1] != 0) {
			return memo[idx][prevColor + 1];
		}
		memo[idx][prevColor + 1] =
				IntStream.range(0, costs[0].length)
						.filter(color -> color != prevColor)
						.map(color -> costs[idx][color] + solve(idx + 1, color, costs, memo))
						.min()
						.orElseThrow();
		return memo[idx][prevColor + 1];
	}

	static void main() {
		var paintHouse2 = new PaintHouse2();
		System.out.println(
				paintHouse2.minCostII(new int[][] {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}})); // 10
		System.out.println(paintHouse2.minCostII(new int[][] {{5}})); // 5

		System.out.println(
				paintHouse2.minCostIITopDown(new int[][] {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}})); // 10
		System.out.println(paintHouse2.minCostIITopDown(new int[][] {{5}})); // 5
	}
}
