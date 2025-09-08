package practice.graph.dijkstra.tracking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 31 Aug 2025 09:17 */

/** [1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) */
public class PathWithMinimumEffort {
	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	public int minimumEffortPath(int[][] heights) {
		// ! Greedily pick the path with minimum `maxEffortInPath`
		var minHeap = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[2]));
		minHeap.add(new int[] {0, 0, 0});
		var visited = new boolean[heights.length][heights[0].length];
		while (!minHeap.isEmpty()) {
			var cell = minHeap.poll();
			var row = cell[0];
			var col = cell[1];
			var maxEffortInPath = cell[2];
			if (row == heights.length - 1 && col == heights[0].length - 1) {
				return maxEffortInPath;
			}
			// ! visit-on-dequeue. This is Dijkstra with a difference that the `maxEffortInPath` is more
			// ! like shortestDistance, but instead of sum it's maxDiff.
			// ! So `minHeap` should handle which `(row, col)` to visit first.
			// ! We shouldn't mark it ourselves in the order we visit.
			if (!visited[row][col]) {
				visited[row][col] = true;
				Arrays.stream(directions)
						.map(d -> new int[] {d[0] + row, d[1] + col})
						.filter(nextCell -> isValid(nextCell, heights))
						.forEach(
								nextCell -> {
									var nextEffort = Math.abs(heights[nextCell[0]][nextCell[1]] - heights[row][col]);
									var nextMaxEffortInPath = Math.max(maxEffortInPath, nextEffort);
									minHeap.add(new int[] {nextCell[0], nextCell[1], nextMaxEffortInPath});
								});
			}
		}
		return -1;
	}

	private static boolean isValid(int[] cell, int[][] heights) {
		return cell[0] >= 0 && cell[0] < heights.length && cell[1] >= 0 && cell[1] < heights[0].length;
	}

	static void main() {
		var pathWithMinimumEffort = new PathWithMinimumEffort();
		System.out.println(
				pathWithMinimumEffort.minimumEffortPath(
						new int[][] {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}})); // 2
		System.out.println(
				pathWithMinimumEffort.minimumEffortPath(
						new int[][] {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}})); // 1
		System.out.println(
				pathWithMinimumEffort.minimumEffortPath(
						new int[][] {
							{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}
						})); // 0
	}
}
