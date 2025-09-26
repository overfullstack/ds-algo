package practice.graph.grid.maze;

import java.util.ArrayDeque;

/** [787 · The Maze](https://www.lintcode.com/problem/787/) */
public class Maze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		var queue = new ArrayDeque<int[]>();
		queue.add(start);
		var visited = new boolean[maze.length][maze[0].length];
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			var cell = queue.removeFirst();
			for (var direction : directions) {
				var nextRow = cell[0];
				var nextCol = cell[1];
				while (isValid(nextRow + direction[0], nextCol + direction[1], maze)
						&& maze[nextRow + direction[0]][nextCol + direction[1]] != 1) {
					nextRow += direction[0];
					nextCol += direction[1];
				}
				if (nextRow == destination[0] && nextCol == destination[1]) {
					return true;
				}
				if (!visited[nextRow][nextCol]) {
					visited[nextRow][nextCol] = true;
					queue.add(new int[] {nextRow, nextCol});
				}
			}
		}
		return false;
	}

	private boolean isValid(int row, int col, int[][] maze) {
		return (row >= 0 && row < maze.length) && (col >= 0 && col < maze[0].length);
	}

	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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
		var mazeSolver = new Maze();
		System.out.println(mazeSolver.hasPath(maze, start, destination)); // true
	}
}
