package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 31 Aug 2025 09:17 */

/** [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) */
public class PathWithMinimumEffort {
	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	public int minimumEffortPath(int[][] heights) {
		var pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[2]));
		pq.add(new int[] {0, 0, 0});
		var visited = new boolean[heights.length][heights[0].length];
		visited[0][0] = true;
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			var row = cell[0];
			var col = cell[1];
			var maxEffortInPath = cell[2];
			if (row == heights.length - 1 && col == heights[0].length - 1) {
				return maxEffortInPath;
			}
			visited[row][col] = true;
			Arrays.stream(directions)
					.map(d -> new int[] {d[0] + row, d[1] + col})
					.filter(nextCell -> isValid(nextCell, heights) && !visited[nextCell[0]][nextCell[1]])
					.forEach(
							nextCell -> {
								var nextEffort = Math.abs(heights[nextCell[0]][nextCell[1]] - heights[row][col]);
								var nextMaxEffortInPath = Math.max(maxEffortInPath, nextEffort);
								pq.add(new int[] {nextCell[0], nextCell[1], nextMaxEffortInPath});
							});
		}
		return -1;
	}

	private static boolean isValid(int[] cell, int[][] heights) {
		return cell[0] >= 0 && cell[0] < heights.length && cell[1] >= 0 && cell[1] < heights[0].length;
	}

	static void main() {
		var pathWithMinimumEffort = new PathWithMinimumEffort();
		var heights = new int[][] {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
		System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
		heights = new int[][] {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
		System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
		heights =
				new int[][] {
					{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}
				};
		System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
	}
}
