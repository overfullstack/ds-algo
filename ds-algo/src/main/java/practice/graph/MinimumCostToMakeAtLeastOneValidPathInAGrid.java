package practice.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 04 Sep 2025 08:39 */

/**
 * [1368. Minimum Cost to Make at Least One Valid Path in a
 * Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {
	public int minCost(int[][] grid) { // * 0-1 BFS, more performant O(V+E)
		var queue = new ArrayDeque<int[]>();
		var visited = new boolean[grid.length][grid[0].length];
		queue.add(new int[] {0, 0, 0});
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			var row = cell[0];
			var col = cell[1];
			var cost = cell[2];
			if (row == grid.length - 1 && col == grid[0].length - 1) {
				return cost;
			}
			if (!visited[row][col]) {
				visited[row][col] = true;
				for (var direction : directions) {
					var nextRow = row + direction[0];
					var nextCol = col + direction[1];
					// ! We can't mark before enqueue as the first time you discover a (row, col)
					// ! does not mean it's via its optimal path, as we have variable weights (0, 1)
					// ! Just like `pq` we should leave it on the dequeue to provide us the optimal path
					if (isValid(nextRow, nextCol, grid)) {
						// ! Doing the sorting ourselves like `pq` as there are only two values 0, 1
						if (direction[2] == grid[row][col]) {
							queue.addFirst(new int[] {nextRow, nextCol, cost});
						} else {
							queue.addLast(new int[] {nextRow, nextCol, cost + 1});
						}
					}
				}
			}
		}
		return -1;
	}

	public int minCost2(int[][] grid) { // * Dijkstra, O((V+E)logV)
		// ! Uneven neighbor weights, one is same direction has cost `0` and the others have cost `1`
		// ! So using Dijkstra instead of BFS
		var pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(c -> c[2]));
		var minCost = new int[grid.length][grid[0].length];
		for (var c : minCost) {
			Arrays.fill(c, Integer.MAX_VALUE);
		}
		pq.add(new int[] {0, 0, 0});
		// ! Shouldn't add `minCost[0][0] = 0;` in Dijkstra as we do `cost < minCost[row][col]` check
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			var row = cell[0];
			var col = cell[1];
			var cost = cell[2];
			if (cost < minCost[row][col]) {
				minCost[row][col] = cost;
				for (var direction : directions) {
					var nextRow = row + direction[0];
					var nextCol = col + direction[1];
					var nextCost = (direction[2] == grid[row][col]) ? cost : cost + 1;
					if (isValid(nextRow, nextCol, grid) && nextCost < minCost[nextRow][nextCol]) {
						pq.add(new int[] {nextRow, nextCol, nextCost});
					}
				}
			}
		}
		return minCost[grid.length - 1][grid[0].length - 1];
	}

	private static boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}

	// ! { y, x, direction } - 1: right, 2: left, 3: down, 4: up
	private static final int[][] directions = 
			new int[][] {{0, 1, 1}, {0, -1, 2}, {1, 0, 3}, {-1, 0, 4}};

	static void main() {
		var obj = new MinimumCostToMakeAtLeastOneValidPathInAGrid();
		System.out.println(
				obj.minCost(new int[][] {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}})); // 3
		System.out.println(obj.minCost(new int[][] {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}})); // 0
		System.out.println(obj.minCost(new int[][] {{1, 2}, {4, 3}})); // 1

		System.out.println(
				obj.minCost2(new int[][] {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}})); // 3
		System.out.println(obj.minCost2(new int[][] {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}})); // 0
		System.out.println(obj.minCost2(new int[][] {{1, 2}, {4, 3}})); // 1
	}
}
