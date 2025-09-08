package practice.graph.grid;

import java.util.ArrayDeque;

/* 06 Sep 2025 13:06 */

/**
 * [1926. Nearest Exit from Entrance in
 * Maze](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/)
 */
public class NearestExitFromEntranceInMaze {
	public int nearestExit(char[][] maze, int[] entrance) {
		var queue = new ArrayDeque<int[]>();
		var rows = maze.length;
		var cols = maze[0].length;
		var visited = new boolean[rows][cols];
		for (var row = 0; row < rows; row++) {
			if (!(entrance[0] == row && entrance[1] == 0) && maze[row][0] == '.') {
				visited[row][0] = true;
				queue.add(new int[] {row, 0, 0});
			}
			if (!(entrance[0] == row && entrance[1] == cols - 1) && maze[row][cols - 1] == '.') {
				visited[row][cols - 1] = true;
				queue.add(new int[] {row, cols - 1, 0});
			}
		}
		for (var col = 1; col < cols - 1; col++) {
			if (!(entrance[0] == 0 && entrance[1] == col) && maze[0][col] == '.') {
				visited[0][col] = true;
				queue.add(new int[] {0, col, 0});
			}
			if (!(entrance[0] == rows - 1 && entrance[1] == col) && maze[rows - 1][col] == '.') {
				visited[rows - 1][col] = true;
				queue.add(new int[] {rows - 1, col, 0});
			}
		}
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			var row = cell[0];
			var col = cell[1];
			var distance = cell[2];
			var nextDistance = distance + 1;
			for (var direction : directions) {
				var nextRow = row + direction[0];
				var nextCol = col + direction[1];
				if (isValid(nextRow, nextCol, maze)
						&& !visited[nextRow][nextCol]
						&& maze[nextRow][nextCol] == '.') {
					if (nextRow == entrance[0] && nextCol == entrance[1]) {
						return nextDistance;
					}
					visited[nextRow][nextCol] = true;
					queue.add(new int[] {nextRow, nextCol, nextDistance});
				}
			}
		}
		return -1;
	}

	private static final int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	private static boolean isValid(int row, int col, char[][] maze) {
		return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length;
	}

	static void main() {
		var obj = new NearestExitFromEntranceInMaze();
		char[][] maze5 = {
			{'.', '+', '+', '+'},
			{'+', '+', '+', '+'},
			{'+', '+', '+', '.'},
			{'+', '.', '.', '+'}
		};
		int[] entrance5 = {0, 0};
		System.out.println(obj.nearestExit(maze5, entrance5)); // 7
		char[][] maze4 = {{'.', '.'}};
		int[] entrance4 = {0, 1};
		System.out.println(obj.nearestExit(maze4, entrance4)); // 1
		char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
		int[] entrance = {1, 2};
		System.out.println(obj.nearestExit(maze, entrance)); // 1
		char[][] maze2 = {{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
		int[] entrance2 = {1, 0};
		System.out.println(obj.nearestExit(maze2, entrance2)); // 2
		char[][] maze3 = {{'.', '+'}};
		int[] entrance3 = {0, 0};
		System.out.println(obj.nearestExit(maze3, entrance3)); // -1
	}
}
