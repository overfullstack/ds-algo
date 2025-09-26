package practice.graph.grid.maze;

import ds.util.Pair;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 26 Sep 2025 16:19 */

/** [499 - The Maze III](https://leetcode.com/problems/the-maze-iii/) */
public class Maze3 {
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		var pq =
				new PriorityQueue<>(
						Comparator.<Pair<int[], String>>comparingInt(p -> p.first()[2])
								.thenComparing(Pair::second)); // ! Need Lexicographically smaller path
		pq.add(Pair.of(new int[] {ball[0], ball[1], 0}, ""));
		var minDistances = new int[maze.length][maze[0].length];
		var minPaths = new String[maze.length][maze[0].length];
		for (var minDistance : minDistances) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
		}
		minDistances[ball[0]][ball[1]] = 0;
		minPaths[ball[0]][ball[1]] = "";
		while (!pq.isEmpty()) {
			var cell = pq.poll();
			var row = cell.first()[0];
			var col = cell.first()[1];
			var distanceFromSource = cell.first()[2];
			var path = cell.second();
			if (row == hole[0] && col == hole[1]) {
				return path;
			}
			// ! same or lesser distance and lexicographically same or smaller path
			if (distanceFromSource <= minDistances[row][col]
					&& (minPaths[row][col] == null || path.compareTo(minPaths[row][col]) <= 0)) {
				for (var direction : directions) {
					var nextRow = row;
					var nextCol = col;
					var nextPath = path + (char) direction[2];
					var distanceFromCellToNext = 0;
					while (isValid(nextRow + direction[0], nextCol + direction[1], maze)
							&& maze[nextRow + direction[0]][nextCol + direction[1]] != 1
							&& !(nextRow == hole[0] && nextCol == hole[1])) { // ! Stop at hole
						nextRow += direction[0];
						nextCol += direction[1];
						distanceFromCellToNext++;
					}
					var nextDistance = distanceFromSource + distanceFromCellToNext;
					if (nextDistance < minDistances[nextRow][nextCol]
							|| (nextDistance == minDistances[nextRow][nextCol]
									&& (minPaths[nextRow][nextCol] == null
											|| nextPath.compareTo(minPaths[nextRow][nextCol]) < 0))) {
						minDistances[nextRow][nextCol] = nextDistance;
						minPaths[nextRow][nextCol] = nextPath;
						pq.add(Pair.of(new int[] {nextRow, nextCol, nextDistance}, nextPath));
					}
				}
			}
		}
		return "impossible";
	}

	private static final int[][] directions = {{1, 0, 'd'}, {0, -1, 'l'}, {0, 1, 'r'}, {-1, 0, 'u'}};

	private static boolean isValid(int row, int col, int[][] maze) {
		return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length;
	}

	static void main() {
		var maze3 = new Maze3();

		// Test Case 1: Basic case with valid path
		var maze1 =
				new int[][] {
					{0, 0, 0, 0, 0},
					{1, 1, 0, 0, 1},
					{0, 0, 0, 0, 0},
					{0, 1, 0, 0, 1},
					{0, 1, 0, 0, 0}
				};
		var ball1 = new int[] {4, 3};
		var hole1 = new int[] {0, 1};
		System.out.println(
				"Test 1 - Expected: lul, Actual: " + maze3.findShortestWay(maze1, ball1, hole1));

		// Test Case 2: Impossible case
		var maze2 =
				new int[][] {
					{0, 0, 0, 0, 0},
					{1, 1, 0, 0, 1},
					{0, 0, 0, 0, 0},
					{0, 1, 0, 0, 1},
					{0, 1, 0, 0, 0}
				};
		var ball2 = new int[] {4, 3};
		var hole2 = new int[] {3, 0};
		System.out.println(
				"Test 2 - Expected: impossible, Actual: " + maze3.findShortestWay(maze2, ball2, hole2));

		// Test Case 3: Complex path
		var maze3_test =
				new int[][] {
					{0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 0, 0, 1, 0},
					{0, 0, 0, 0, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 1}
				};
		var ball3 = new int[] {0, 4};
		var hole3 = new int[] {3, 5};
		System.out.println(
				"Test 3 - Expected: dldr, Actual: " + maze3.findShortestWay(maze3_test, ball3, hole3));

		// Test Case 4: Ball already at hole
		var maze4 =
				new int[][] {
					{0, 0, 0},
					{0, 1, 0},
					{0, 0, 0}
				};
		var ball4 = new int[] {1, 0};
		var hole4 = new int[] {1, 0};
		System.out.println(
				"Test 4 - Expected: , Actual: " + maze3.findShortestWay(maze4, ball4, hole4));

		// Test Case 5: Single cell maze
		var maze5 = new int[][] {{0}};
		var ball5 = new int[] {0, 0};
		var hole5 = new int[] {0, 0};
		System.out.println(
				"Test 5 - Expected: , Actual: " + maze3.findShortestWay(maze5, ball5, hole5));

		// Test Case 6: Lexicographically smaller path (same distance, prefer "d" over
		// "r")
		var maze6 =
				new int[][] {
					{0, 0, 0},
					{0, 0, 0},
					{0, 0, 0}
				};
		var ball6 = new int[] {0, 0};
		var hole6 = new int[] {2, 2};
		System.out.println(
				"Test 6 - Expected: dr, Actual: " + maze3.findShortestWay(maze6, ball6, hole6));

		// Test Case 7: Hole in the middle - ball cannot reach without walls to stop it
		var maze7 =
				new int[][] {
					{0, 0, 0, 0, 0},
					{0, 1, 0, 1, 0},
					{0, 0, 0, 0, 0},
					{0, 1, 0, 1, 0},
					{0, 0, 0, 0, 0}
				};
		var ball7 = new int[] {0, 0};
		var hole7 = new int[] {2, 2};
		System.out.println(
				"Test 7 - Expected: impossible, Actual: " + maze3.findShortestWay(maze7, ball7, hole7));

		// Test Case 7b: Simple "dr" case - hole at corner where ball naturally stops
		var maze7b =
				new int[][] {
					{0, 0, 0},
					{0, 0, 0},
					{0, 0, 0}
				};
		var ball7b = new int[] {0, 0};
		var hole7b = new int[] {2, 2};
		System.out.println(
				"Test 7b - Expected: dr, Actual: " + maze3.findShortestWay(maze7b, ball7b, hole7b));

		// Test Case 8: Multiple paths with same distance - lexicographically first
		var maze8 =
				new int[][] {
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0},
					{0, 0, 0, 0}
				};
		var ball8 = new int[] {0, 0};
		var hole8 = new int[] {3, 3};
		System.out.println(
				"Test 8 - Expected: dr, Actual: " + maze3.findShortestWay(maze8, ball8, hole8));

		// Test Case 9: Wall blocking direct path - must go around
		var maze9 =
				new int[][] {
					{0, 0, 0},
					{1, 1, 1},
					{0, 0, 0}
				};
		var ball9 = new int[] {0, 1};
		var hole9 = new int[] {2, 1};
		System.out.println(
				"Test 9 - Expected: impossible, Actual: " + maze3.findShortestWay(maze9, ball9, hole9));

		// Test Case 10: Hole at corner
		var maze10 =
				new int[][] {
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0}
				};
		var ball10 = new int[] {1, 0};
		var hole10 = new int[] {0, 0};
		System.out.println(
				"Test 10 - Expected: u, Actual: " + maze3.findShortestWay(maze10, ball10, hole10));

		// Test Case 11: Long straight path
		var maze11 =
				new int[][] {
					{0, 0, 0, 0, 0, 0},
					{1, 1, 1, 1, 1, 1},
					{0, 0, 0, 0, 0, 0}
				};
		var ball11 = new int[] {0, 0};
		var hole11 = new int[] {0, 5};
		System.out.println(
				"Test 11 - Expected: r, Actual: " + maze3.findShortestWay(maze11, ball11, hole11));

		// Test Case 12: Hole right next to ball - but ball will overshoot
		var maze12 =
				new int[][] {
					{0, 0, 0, 0},
					{0, 0, 0, 0},
					{0, 0, 0, 0}
				};
		var ball12 = new int[] {1, 1};
		var hole12 = new int[] {1, 2};
		System.out.println(
				"Test 12 - Expected: r, Actual: " + maze3.findShortestWay(maze12, ball12, hole12));

		// Test Case 13: Complex maze with multiple possible routes
		var maze13 =
				new int[][] {
					{0, 0, 0, 0, 0},
					{0, 1, 0, 1, 0},
					{0, 0, 0, 0, 0},
					{0, 1, 0, 1, 0},
					{0, 0, 0, 0, 0}
				};
		var ball13 = new int[] {0, 0};
		var hole13 = new int[] {4, 4};
		System.out.println(
				"Test 13 - Expected: dr, Actual: " + maze3.findShortestWay(maze13, ball13, hole13));

		// Test Case 14: Ball surrounded by walls
		var maze14 =
				new int[][] {
					{1, 1, 1},
					{1, 0, 1},
					{1, 1, 1}
				};
		var ball14 = new int[] {1, 1};
		var hole14 = new int[] {0, 0};
		System.out.println(
				"Test 14 - Expected: impossible, Actual: " + maze3.findShortestWay(maze14, ball14, hole14));

		// Test Case 15: Minimum size maze with valid solution
		var maze15 =
				new int[][] {
					{0, 0},
					{0, 0}
				};
		var ball15 = new int[] {0, 0};
		var hole15 = new int[] {1, 1};
		System.out.println(
				"Test 15 - Expected: dr, Actual: " + maze3.findShortestWay(maze15, ball15, hole15));
	}
}
