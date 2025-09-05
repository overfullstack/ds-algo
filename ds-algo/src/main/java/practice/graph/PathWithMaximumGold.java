package practice.graph;

import java.util.Arrays;

/* 31 Aug 2025 20:47 */

/** [1219. Path with Maximum Gold](https://leetcode.com/problems/path-with-maximum-gold/) */
public class PathWithMaximumGold {
	public int getMaximumGold(int[][] grid) {
		var maxGold = 0;
		for (var row = 0; row < grid.length; row++) {
			for (var col = 0; col < grid[0].length; col++) {
				maxGold = Math.max(maxGold, dfs(row, col, grid));
			}
		}
		return maxGold;
	}

	private static int dfs(int row, int col, int[][] grid) {
		if (!isValid(row, col, grid) || grid[row][col] == 0) {
			return 0;
		}
		var gold = grid[row][col];
		grid[row][col] = 0; // ! Visiting
		var maxGold =
				gold
						+ Arrays.stream(directions)
								.map(d -> new int[] {d[0] + row, d[1] + col})
								.mapToInt(nextCell -> dfs(nextCell[0], nextCell[1], grid))
								.max()
								.orElse(0);
		grid[row][col] = gold; // ! Backtracking. Un-visiting, for other origins to use it
		return maxGold;
	}

	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	private static boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}

	static void main() {
		var obj = new PathWithMaximumGold();
		var grid = new int[][] {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
		System.out.println(obj.getMaximumGold(grid));
	}
}
