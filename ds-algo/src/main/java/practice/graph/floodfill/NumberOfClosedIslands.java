package practice.graph.floodfill;

import java.util.Arrays;

/* 26 Aug 2025 20:50 */

/** [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/) */
public class NumberOfClosedIslands {

	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int closedIsland(int[][] grid) {
		for (var row = 0; row < grid.length; row++) {
			floodFill(row, 0, grid);
			floodFill(row, grid[0].length - 1, grid);
		}

		for (var col = 0; col < grid[0].length; col++) {
			floodFill(0, col, grid);
			floodFill(grid.length - 1, col, grid);
		}

		var closedIslandCount = 0;
		for (var row = 0; row < grid.length; row++) {
			for (var col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 0) {
					closedIslandCount++;
					floodFill(row, col, grid); // ! Fill them so we count the group as one island
				}
			}
		}
		return closedIslandCount;
	}

	private static void floodFill(int row, int col, int[][] grid) {
		if (!isValid(row, col, grid) || grid[row][col] != 0) { // ! Fill only land cells
			return;
		}
		grid[row][col] = 1;
		Arrays.stream(directions)
				.map(d -> new int[] {d[0] + row, d[1] + col})
				.forEach(landCell -> floodFill(landCell[0], landCell[1], grid));
	}

	private static boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}

	static void main() {
		var grid =
				new int[][] {
					{1, 1, 1, 1, 1, 1, 1, 0},
					{1, 0, 0, 0, 0, 1, 1, 0},
					{1, 0, 1, 0, 1, 1, 1, 0},
					{1, 0, 0, 0, 0, 1, 0, 1},
					{1, 1, 1, 1, 1, 1, 1, 0}
				};
		System.out.println(new NumberOfClosedIslands().closedIsland(grid)); // 2
	}
}
