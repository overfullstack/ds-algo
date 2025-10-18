package practice.backtracking;

/* 18 Oct 2025 11:27 */

public class MinimumMovesToSpreadStonesOverGrid {
	public int minimumMoves(int[][] grid) {
		/*
		! This functional approach passes test case individually but giving TLE when submitted
		var emptyCellFound = Arrays.stream(grid)
				.anyMatch(row -> Arrays.stream(row).anyMatch(col -> col == 0));
		*/
		var emptyCellFound = false;
		for (var i = 0; i < 3 && !emptyCellFound; ++i) {
			for (var j = 0; j < 3; ++j) {
				if (grid[i][j] == 0) {
					emptyCellFound = true;
					break;
				}
			}
		}
		if (!emptyCellFound) {
			return 0;
		}
		var result = Integer.MAX_VALUE;
		for (var row = 0; row < grid.length; row++) {
			for (var col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 0) {
					for (var nextRow = 0; nextRow < grid.length; nextRow++) {
						for (var nextCol = 0; nextCol < grid.length; nextCol++) {
							if (grid[nextRow][nextCol] > 1) {
								var distance = Math.abs(nextRow - row) + Math.abs(nextCol - col);
								grid[nextRow][nextCol]--;
								grid[row][col]++;
								result = Math.min(result, distance + minimumMoves(grid));
								grid[nextRow][nextCol]++;
								grid[row][col]--;
							}
						}
					}
				}
			}
		}
		return result;
	}

	static void main() {
		var sol = new MinimumMovesToSpreadStonesOverGrid();
		int[][] grid = {{1, 1, 0}, {1, 1, 1}, {1, 2, 1}};
		System.out.println("Minimum moves to spread stones over grid: " + sol.minimumMoves(grid)); // 3
		int[][] grid2 = {{1, 3, 0}, {1, 0, 0}, {1, 0, 3}};
		System.out.println(
				"Minimum moves to spread stones over grid2: " + sol.minimumMoves(grid2)); // 4
	}
}
