package practice.graph.grid.maze;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** [788 · The Maze II](https://www.lintcode.com/problem/788) */
public class Maze2 {
	public int shortestDistance(int[][] maze, int[] start, int[] destination) { // * Dijkstra
		var pq = new PriorityQueue<int[]>(Comparator.comparingInt(c -> c[2]));
		pq.add(new int[] {start[0], start[1], 0});
		var minDistances = new int[maze.length][maze[0].length];
		for (var minDistance : minDistances) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
		}
		minDistances[start[0]][start[1]] = 0; // ! init
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			// ! Can't stop while moving in a direction as per problem
			// ! so destination verified only after dequeue
			if (cell[0] == destination[0] && cell[1] == destination[1]) {
				return cell[2];
			}
			var row = cell[0];
			var col = cell[1];
			var distanceFromSource = cell[2];
			if (distanceFromSource <= minDistances[row][col]) {
				for (var direction : directions) {
					var nextRow = row; // ! Reset to current cell before taking a different direction
					var nextCol = col;
					var distanceFromCellToNext = 0;
					// ! Finding next stop and measure the distance between `From` and `To`
					// ! isValid is checked without mutating `nextRow` and `nextCol` with direction
					// ! so that we end up with the last valid `nextRow` and `nextCol`
					while (isValid(nextRow + direction[0], nextCol + direction[1], maze)
							&& maze[nextRow + direction[0]][nextCol + direction[1]] != 1) {
						nextRow += direction[0];
						nextCol += direction[1];
						distanceFromCellToNext++;
					}
					var nextDistance = distanceFromSource + distanceFromCellToNext; // ! Variable weights
					if (nextDistance < minDistances[nextRow][nextCol]) {
						minDistances[nextRow][nextCol] = nextDistance;
						pq.add(new int[] {nextRow, nextCol, nextDistance});
					}
				}
			}
		}
		return -1;
	}

	private boolean isValid(int row, int col, int[][] maze) {
		return (row >= 0 && row < maze.length) && (col >= 0 && col < maze[0].length);
	}

	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	// ! BFS with relaxation or modified BFS for weighted graphs
	// ! Not optimal compared to Dijkstra's algorithm
	// ! Works better when the weights are constant
	public int shortestDistanceBFS(int[][] maze, int[] start, int[] destination) {
		var queue = new ArrayDeque<int[]>();
		queue.add(start);
		var minDistances = new int[maze.length][maze[0].length];
		for (var minDistance : minDistances) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
		}
		minDistances[start[0]][start[1]] = 0;
		while (!queue.isEmpty()) {
			var cell = queue.removeFirst();
			for (var direction : directions) {
				var nextRow = cell[0];
				var nextCol = cell[1];
				var nextDistance = minDistances[nextRow][nextCol];
				while (isValid(nextRow + direction[0], nextCol + direction[1], maze)
						&& maze[nextRow + direction[0]][nextCol + direction[1]] != 1) {
					nextRow += direction[0];
					nextCol += direction[1];
					nextDistance++;
				}
				// ! letting multiple paths flow through
				if (nextDistance < minDistances[nextRow][nextCol]) {
					minDistances[nextRow][nextCol] = nextDistance;
					queue.add(new int[] {nextRow, nextCol});
				}
			}
		}
		return minDistances[destination[0]][destination[1]] == Integer.MAX_VALUE
				? -1
				: minDistances[destination[0]][destination[1]];
	}

	static void main() {
		var maze =
				new int[][] {
					{0, 0, 1, 0, 0},
					{0, 0, 0, 0, 0},
					{0, 0, 0, 1, 0},
					{1, 1, 0, 1, 1},
					{0, 0, 0, 0, 0}
				};
		var start = new int[] {0, 4};
		var destination = new int[] {4, 4};
		System.out.println(new Maze2().shortestDistance(maze, start, destination)); // 12

		var maze2 =
				new int[][] {
					{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}
				};
		int[] start2 = {0, 4};
		int[] destination2 = {0, 0};
		System.out.println(new Maze2().shortestDistance(maze2, start2, destination2)); // 6

		int[][] maze3 = {
			{0, 0, 0, 1, 1, 1, 1, 1},
			{0, 1, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[] start3 = {3, 0};
		int[] destination3 = {1, 2};
		System.out.println(new Maze2().shortestDistance(maze3, start3, destination3)); // 6
	}
}
