package practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/* 28 Aug 2025 10:24 */

/**
 * [2812. Find the Safest Path in a
 * Grid](https://leetcode.com/problems/find-the-safest-path-in-a-grid/)
 */
public class FindTheSafestPathInGrid {
	public int maximumSafenessFactor(List<List<Integer>> grid) {
		var minDistance = minDistanceFromThiefWithBFS(grid);
		// ! Pick the path with Max (min distance in path) to Thief
		var pq = new PriorityQueue<>(Comparator.comparingInt((int[] c) -> c[2]).reversed());
		pq.add(new int[] {0, 0, minDistance[0][0]});
		var visited = new boolean[grid.size()][grid.getFirst().size()];
		visited[0][0] = true;
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			var row = cell[0];
			var col = cell[1];
			var minInPath = cell[2];
			if (row == grid.size() - 1 && col == grid.getFirst().size() - 1) {
				return minInPath; // ! min distance to Thief in the path
			}
			Arrays.stream(directions)
					.map(d -> new int[] {d[0] + row, d[1] + col})
					// ! visited instead of distance check
					// ! as we have unit weights and first visit with BFS is always optimal
					.filter(nextCell -> isValid2(nextCell, minDistance) && !visited[nextCell[0]][nextCell[1]])
					.forEach(
							nextCell -> {
								visited[nextCell[0]][nextCell[1]] = true;
								// ! The entire path carries that min distance from current and next cells to Thief
								var nextMinInPath = Math.min(minInPath, minDistance[nextCell[0]][nextCell[1]]);
								pq.add(new int[] {nextCell[0], nextCell[1], nextMinInPath});
							});
		}
		return -1;
	}

	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	private static int[][] minDistanceFromThiefWithBFS(List<List<Integer>> grid) {
		// ! ArrayDeque with visited, as we have unit weights and first visit with BFS is always optimal
		var pq = new ArrayDeque<int[]>();
		var visited = new boolean[grid.size()][grid.getFirst().size()];
		var minDistance = new int[grid.size()][grid.getFirst().size()];
		for (var row = 0; row < grid.size(); row++) {
			for (var col = 0; col < grid.getFirst().size(); col++) {
				if (grid.get(row).get(col) == 1) {
					pq.add(new int[] {row, col, 0});
				}
			}
		}
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			var row = cell[0];
			var col = cell[1];
			var distance = cell[2];
			minDistance[row][col] = distance;
			Arrays.stream(directions)
					.map(d -> new int[] {d[0] + row, d[1] + col})
					.filter(
							nextCell ->
									isValid(nextCell, grid)
											&& !visited[nextCell[0]][nextCell[1]]
											&& grid.get(nextCell[0]).get(nextCell[1]) != 1)
					.forEach(
							nextCell -> {
								visited[nextCell[0]][nextCell[1]] = true;
								pq.add(new int[] {nextCell[0], nextCell[1], distance + 1});
							});
		}
		return minDistance;
	}

	private static boolean isValid(int[] cell, List<List<Integer>> grid) {
		return cell[0] >= 0
				&& cell[0] < grid.size()
				&& cell[1] >= 0
				&& cell[1] < grid.getFirst().size();
	}

	private static boolean isValid2(int[] cell, int[][] grid) {
		return cell[0] >= 0 && cell[0] < grid.length && cell[1] >= 0 && cell[1] < grid[0].length;
	}

	static void main() {
		var findTheSafestPathInGrid = new FindTheSafestPathInGrid();
		var grid = List.of(List.of(0, 0, 1), List.of(0, 0, 0), List.of(0, 0, 0));
		System.out.println(findTheSafestPathInGrid.maximumSafenessFactor(grid));
	}
}
