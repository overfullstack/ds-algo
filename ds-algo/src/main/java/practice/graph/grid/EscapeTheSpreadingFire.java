package practice.graph.grid;

import java.util.ArrayDeque;
import java.util.Arrays;

/* 27 Sep 2025 12:07 */

/** [2258. Escape the Spreading Fire](https://leetcode.com/problems/escape-the-spreading-fire/) */
public class EscapeTheSpreadingFire {
	public int maximumMinutes(int[][] grid) {
		var queue = new ArrayDeque<int[]>();
		var minFireArrivalTimes = new int[grid.length][grid[0].length];
		for (var minTime : minFireArrivalTimes) {
			Arrays.fill(minTime, Integer.MAX_VALUE);
		}
		for (var row = 0; row < grid.length; row++) {
			for (var col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					queue.add(new int[] {row, col, 0});
					minFireArrivalTimes[row][col] = 0;
				}
			}
		}
		while (!queue.isEmpty()) { // * BFS
			var cell = queue.removeFirst();
			var row = cell[0];
			var col = cell[1];
			var time = cell[2];
			if (time <= minFireArrivalTimes[row][col]) {
				Arrays.stream(directions)
						.map(d -> new int[] {row + d[0], col + d[1]})
						.filter(
								c ->
										isValid(c[0], c[1], grid)
												&& grid[c[0]][c[1]] != 2 // ! `2` is Wall cell
												&& time + 1 < minFireArrivalTimes[c[0]][c[1]])
						.forEach(
								c -> {
									minFireArrivalTimes[c[0]][c[1]] = time + 1;
									queue.add(new int[] {c[0], c[1], time + 1});
								});
			}
		}
		// * Rightmost maxWaitTime Binary search
		var left = 0;
		var right = 1_000_000_000;
		while (left <= right) {
			var mid = left + (right - left) / 2;
			if (canCross(grid, minFireArrivalTimes, mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	private static boolean canCross(int[][] grid, int[][] minFireArrivalTime, int waitTime) { // * BFS
		var queue = new ArrayDeque<int[]>();
		queue.add(new int[] {0, 0, waitTime});
		var visited = new boolean[grid.length][grid[0].length];
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			var cell = queue.removeFirst();
			var row = cell[0];
			var col = cell[1];
			var time = cell[2];
			for (var d : directions) {
				var nextRow = row + d[0];
				var nextCol = col + d[1];
				if (isValid(nextRow, nextCol, grid)
						&& !visited[nextRow][nextCol]
						&& grid[nextRow][nextCol] != 2) {
					var isSafeHouse = nextRow == grid.length - 1 && nextCol == grid[0].length - 1;
					// ! As per problem, even if the fire spreads to the safehouse immediately after
					// ! you have reached it, it will be counted as safely reaching the safehouse.
					var arrivalTime = time + 1;
					var fireArrivalTime = minFireArrivalTime[nextRow][nextCol];
					var canReachCellBeforeFire =
							isSafeHouse ? arrivalTime <= fireArrivalTime : arrivalTime < fireArrivalTime;
					if (canReachCellBeforeFire) {
						if (isSafeHouse) {
							return true;
						}
						visited[nextRow][nextCol] = true;
						queue.add(new int[] {nextRow, nextCol, arrivalTime});
					}
				}
			}
		}
		return false;
	}

	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	private static boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}

	static void main() {
		var obj = new EscapeTheSpreadingFire();
		int[][] grid = {
			{0, 2, 0, 0, 0, 0, 0},
			{0, 0, 0, 2, 2, 1, 0},
			{0, 2, 0, 0, 1, 2, 0},
			{0, 0, 2, 2, 2, 0, 2},
			{0, 0, 0, 0, 0, 0, 0}
		};
		System.out.println(obj.maximumMinutes(grid)); // 3
		grid = new int[][] {{0, 0, 0, 0}, {0, 1, 2, 0}, {0, 2, 0, 0}};
		System.out.println(obj.maximumMinutes(grid)); // -1
		grid = new int[][] {{0, 0, 0}, {2, 2, 0}, {1, 2, 0}};
		System.out.println(obj.maximumMinutes(grid)); // 1_000_000_000
	}
}
