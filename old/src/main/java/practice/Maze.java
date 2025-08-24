package practice;

import java.util.LinkedList;

public class Maze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		var queue = new LinkedList<int[]>();
		queue.add(start);
		var visited = new boolean[maze.length][maze[0].length];
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			for (var direction : directions) {
				var nextX = cell[0];
				var nextY = cell[1];
				while (isValid(nextX + direction[0], nextY + direction[1], maze)
						&& maze[nextX + direction[0]][nextY + direction[1]] != 1) {
					nextX += direction[0];
					nextY += direction[1];
				}
				if (nextX == destination[0] && nextY == destination[1]) {
					return true;
				}
				if (!visited[nextX][nextY]) {
					visited[nextX][nextY] = true;
					queue.add(new int[] {nextX, nextY});
				}
			}
		}
		return false;
	}

	private boolean isValid(int x, int y, int[][] maze) {
		return (x >= 0 && x < maze.length) && (y >= 0 && y < maze[0].length);
	}

	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public static void main(String[] args) {
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
