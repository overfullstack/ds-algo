package challenges.Maze;

import java.util.Scanner;

/** Created by gakshintala on 4/18/16. */
public class Maze {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var m = scn.nextInt();
		var n = scn.nextInt();

		if (m <= 0 || n <= 0) {
			System.out.println("Invalid Maze");
			return;
		}

		var maze = readMaze(scn, m, n);

		// Traverse through border for open doors
		// Left border
		for (int i = 0, j = 0; i < m; i++) {
			solveMazeForCurrentDoorIn(m, n, maze, i, j);
		}

		// Right border
		if (n - 1 > 0) { // For Single Column matrix
			for (int i = 0, j = n - 1; i < m; i++) {
				solveMazeForCurrentDoorIn(m, n, maze, i, j);
			}
		}

		// Top border
		for (int i = 0, j = 1; j < n - 1; j++) {
			solveMazeForCurrentDoorIn(m, n, maze, i, j);
		}

		// Bottom border
		if (m - 1 > 0) { // For Single Row matrix
			for (int i = m - 1, j = 1; j < n - 1; j++) {
				solveMazeForCurrentDoorIn(m, n, maze, i, j);
			}
		}
	}

	private static void solveMazeForCurrentDoorIn(int m, int n, int[][] maze, int i, int j) {
		if (maze[i][j] == 0) {
			return;
		}

		// Check every reachable door out on the border from current door In.
		System.out.println("Door In: [" + i + "," + j + "] ***************************");
		// Left border
		for (int k = 0, l = 0; k < m; k++) {
			printPathIfReachedDoorOut(m, n, maze, i, j, k, l);
		}

		// Right border
		if (n - 1 > 0) {
			for (int k = 0, l = n - 1; k < m; k++) {
				printPathIfReachedDoorOut(m, n, maze, i, j, k, l);
			}
		}

		// Top border
		for (int k = 0, l = 1; l < n - 1; l++) {
			printPathIfReachedDoorOut(m, n, maze, i, j, k, l);
		}

		// Bottom border
		if (m - 1 > 0) {
			for (int k = m - 1, l = 1; l < n - 1; l++) {
				printPathIfReachedDoorOut(m, n, maze, i, j, k, l);
			}
		}
	}

	private static void printPathIfReachedDoorOut(
			int m, int n, int[][] maze, int i, int j, int k, int l) {
		var visited = new boolean[m][n];
		if (!(i == k && j == l) && maze[k][l] == 1 && isSolved(maze, i, j, k, l, m, n, visited)) {
			printPath(i, j, k, l, m, n, visited);
		}
	}

	private static void printPath(int i, int j, int k, int l, int m, int n, boolean[][] visited) {
		while (!(i == k && j == l)) {
			System.out.print("[" + i + "," + j + "] -> ");
			var next = checkNeighboursForOpenDoor(visited, i, j, m, n);
			i = next[0];
			j = next[1];
		}
		System.out.println("[" + i + "," + j + "] -> Out!!!");
	}

	private static int[] checkNeighboursForOpenDoor(boolean[][] visited, int i, int j, int m, int n) {
		visited[i][j] = false;
		var result = new int[2];
		loop:
		for (var p = i - 1; p <= i + 1; p++) {
			for (var q = j - 1; q <= j + 1; q++) {
				if (p >= 0 && p < m && q >= 0 && q < n && visited[p][q]) {
					result[0] = p;
					result[1] = q;
					break loop;
				}
			}
		}
		return result;
	}

	private static boolean isSolved(
			int[][] maze, int i, int j, int k, int l, int m, int n, boolean[][] visited) {
		if (i == k && j == l) {
			visited[i][j] = true;
			return true;
		}

		var isSolved = false;
		if (isValidMove(maze, i, j, m, n, visited)) {
			visited[i][j] = true;
			// Total 8 directions
			isSolved =
					isSolved(maze, i + 1, j, k, l, m, n, visited)
							|| isSolved(maze, i, j + 1, k, l, m, n, visited)
							|| isSolved(maze, i - 1, j, k, l, m, n, visited)
							|| isSolved(maze, i, j - 1, k, l, m, n, visited)
							|| isSolved(maze, i + 1, j + 1, k, l, m, n, visited)
							|| isSolved(maze, i - 1, j - 1, k, l, m, n, visited)
							|| isSolved(maze, i + 1, j - 1, k, l, m, n, visited)
							|| isSolved(maze, i - 1, j + 1, k, l, m, n, visited);
			visited[i][j] = isSolved;
		}
		return isSolved;
	}

	private static boolean isValidMove(
			int[][] maze, int i, int j, int m, int n, boolean[][] visited) {
		return i >= 0 && i < m && j >= 0 && j < n && !visited[i][j] && maze[i][j] == 1;
	}

	private static int[][] readMaze(Scanner scn, int m, int n) {
		var maze = new int[m][n];
		for (var i = 0; i < m; i++) {
			for (var j = 0; j < n; j++) {
				maze[i][j] = scn.nextInt();
			}
		}
		return maze;
	}
}
