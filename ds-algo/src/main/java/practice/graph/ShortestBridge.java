package practice.graph;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.IntStream;

/* 06 Sep 2025 11:37 */

/** [934. Shortest Bridge](https://leetcode.com/problems/shortest-bridge/) */
public class ShortestBridge {
	public int shortestBridge(int[][] grid) {
		var firstLandCell =
				IntStream.rangeClosed(0, grid.length - 1)
						.mapToObj(
								row ->
										IntStream.rangeClosed(0, grid[row].length - 1)
												.filter(col -> grid[row][col] == 1)
												.mapToObj(col -> new int[] {row, col})
												.findAny())
						.flatMap(Optional::stream)
						.findAny()
						.orElseThrow();
		var queue = new ArrayDeque<int[]>();
		var visited = new boolean[grid.length][grid[0].length];
		dfsGroup(firstLandCell[0], firstLandCell[1], grid, visited, queue);
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			var row = cell[0];
			var col = cell[1];
			var distance = cell[2];
			var nextDistance = distance + 1;
			// ! Can't have visited check here as we start with already visited cells in the queue
			for (var d : directions) {
				var nextRow = row + d[0];
				var nextCol = col + d[1];
				// ! Deeper 1s surrounded by 1s are filtered through visited
				if (isValid(nextRow, nextCol, grid) && !visited[nextRow][nextCol]) {
					if (grid[nextRow][nextCol] == 1) {
						return distance; // ! `distance` and not `nextDistance` as we need bridge length
					}
					visited[nextRow][nextCol] = true;
					queue.add(new int[] {nextRow, nextCol, nextDistance});
				}
			}
		}
		return -1;
	}

	private static void dfsGroup(
			int row, int col, int[][] grid, boolean[][] visited, Queue<int[]> queue) {
		if (!isValid(row, col, grid) || visited[row][col] || grid[row][col] != 1) {
			return;
		}
		visited[row][col] = true;
		// ! 0 distance even for deeper 1s. It doesn't matter as they get filtered during BFS
		queue.add(new int[] {row, col, 0});
		for (var direction : directions) {
			dfsGroup(row + direction[0], col + direction[1], grid, visited, queue);
		}
	}

	private static final int[][] directions = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

	private static boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}

	static void main() {
		var sb = new ShortestBridge();
		System.out.println(sb.shortestBridge(new int[][] {{0, 1}, {1, 0}})); // 1
		System.out.println(sb.shortestBridge(new int[][] {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}})); // 2
		System.out.println(
				sb.shortestBridge(
						new int[][] {
							{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}
						})); // 1
	}
}
