package practice.graph.dijkstra.minmax;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 05 Sep 2025 12:40 */

/**
 * [1102 - Path With Maximum Minimum
 * Value](https://leetcode.ca/2018-12-06-1102-Path-With-Maximum-Minimum-Value/)
 */
public class PathWithMaximumMinimumValue {
	public int maximumMinimumPath(int[][] grid) {
		// ! Greedily pick the path with maximum `minValueInPath`
		var maxHeap = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[2]).reversed());
		var visited = new boolean[grid.length][grid[0].length];
		maxHeap.add(new int[] {0, 0, grid[0][0]});
		while (!maxHeap.isEmpty()) {
			var cell = maxHeap.poll();
			var row = cell[0];
			var col = cell[1];
			var minInPath = cell[2];
			if (row == grid.length - 1 && col == grid[0].length - 1) {
				return minInPath;
			}
			if (!visited[row][col]) {
				visited[row][col] = true;
				Arrays.stream(directions)
						.map(d -> new int[] {d[0] + row, d[1] + col})
						.filter(nc -> isValid(nc, grid))
						.forEach(
								nextCell -> {
									var nextMinInPath = Math.min(minInPath, grid[nextCell[0]][nextCell[1]]);
									maxHeap.add(new int[] {nextCell[0], nextCell[1], nextMinInPath});
								});
			}
		}
		return -1;
	}

	private static final int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	private static boolean isValid(int[] cell, int[][] grid) {
		return cell[0] >= 0 && cell[0] < grid.length && cell[1] >= 0 && cell[1] < grid[0].length;
	}

	static void main() {
		var obj = new PathWithMaximumMinimumValue();
		System.out.println(obj.maximumMinimumPath(new int[][] {{5, 4, 5}, {1, 2, 6}, {7, 3, 4}})); // 4
		System.out.println(
				obj.maximumMinimumPath(new int[][] {{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}})); // 2
		System.out.println(
				obj.maximumMinimumPath(
						new int[][] {
							{3, 4, 6, 3, 4},
							{0, 2, 1, 1, 7},
							{8, 8, 3, 2, 7},
							{3, 2, 4, 9, 8},
							{4, 1, 2, 0, 0},
							{4, 6, 5, 4, 3}
						})); // 3
	}
}
