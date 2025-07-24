package gfg.practice.backtracking.RatMaze;

import java.util.Arrays;

/** Created by gakshintala on 4/7/16. */
public class RatMazeOnePath {
	static void main() {
		int[][] maze = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
		var visited = new boolean[4][4];
		if (solveMaze(maze, 0, 0, visited)) {
			System.out.println("Rat reached the end! :)");
			for (var path : visited) {
				System.out.println(Arrays.toString(path));
			}
		} else {
			System.out.println("Rat can't be out :(");
		}
	}

	private static boolean solveMaze(int[][] maze, int i, int j, boolean[][] visited) {
		if (i == 3 && j == 3) {
			visited[i][j] = true;
			return true; // Checking base case that Rat reached end
		}
		if (isValidMove(i, j, maze)) {
			visited[i][j] = true;
			if (solveMaze(maze, i + 1, j, visited) || solveMaze(maze, i, j + 1, visited)) {
				return true;
			}
			visited[i][j] = false; // backtracking
		}
		return false;
	}

	private static boolean isValidMove(int i, int j, int[][] maze) {
		return i >= 0 && i < 4 && j >= 0 && j < 4 && maze[i][j] != 0;
	}
}
