package practice.graph;

import java.util.Arrays;

/** [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs/) */
public class DetonateMaximumBombs {
	public int maximumDetonation(int[][] bombs) {
		return Arrays.stream(bombs)
				.mapToInt(bomb -> dfs(bomb, bombs, new boolean[bombs.length][bombs.length]))
				.max()
				.orElse(0);
	}

	private static int dfs(int[] bomb, int[][] bombs, boolean[][] visitedPerGroup) {
		visitedPerGroup[bomb[0]][bomb[1]] = true;
		return 1
				+ Arrays.stream(bombs)
						.filter(toBomb -> !visitedPerGroup[toBomb[0]][toBomb[1]] && inRange(toBomb, bomb))
						.mapToInt(toBomb -> dfs(toBomb, bombs, visitedPerGroup))
						.sum();
	}

	private static boolean inRange(int[] toBomb, int[] fromBomb) {
		var dx = toBomb[0] - fromBomb[0];
		var dy = toBomb[1] - fromBomb[1];
		var fromBombRadius = fromBomb[2] * fromBomb[2];
		return (dx * dx + dy * dy) <= fromBombRadius;
	}
}
