package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/** [788 · The Maze II](https://www.lintcode.com/problem/788) */
public class Maze2 {
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		var queue = new PriorityQueue<int[]>(Comparator.comparingInt(c -> c[2]));
		queue.add(new int[] {start[0], start[1], 0});
		var minDistances = new int[maze.length][maze[0].length];
		for (var minDistance : minDistances) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
		}
		minDistances[start[0]][start[1]] = 0;
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			if (cell[0] == destination[0] && cell[1] == destination[1]) {
				return cell[2];
			}
			var distance = cell[2];
			for (var direction : directions) {
				var nextX = cell[0];
				var nextY = cell[1];
				var nextDistance = distance;
				while (isValid(nextX + direction[0], nextY + direction[1], maze)
						&& maze[nextX + direction[0]][nextY + direction[1]] != 1) {
					nextX += direction[0];
					nextY += direction[1];
					nextDistance++; // ! Variable weights
				}
				if (nextDistance < minDistances[nextX][nextY]) {
					minDistances[nextX][nextY] = nextDistance;
					queue.add(new int[] {nextX, nextY, nextDistance});
				}
			}
		}
		return -1;
	}

	private boolean isValid(int x, int y, int[][] maze) {
		return (x >= 0 && x < maze.length) && (y >= 0 && y < maze[0].length);
	}

	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	// ! Not optimal compared to Dijkstra's algorithm
	// ! Works better when the weights are constant
	public int shortestDistanceBFS(int[][] maze, int[] start, int[] destination) {
		var queue = new LinkedList<int[]>();
		queue.add(start);
		var minDistances = new int[maze.length][maze[0].length];
		for (var minDistance : minDistances) {
			Arrays.fill(minDistance, Integer.MAX_VALUE);
		}
		minDistances[start[0]][start[1]] = 0;
		while (!queue.isEmpty()) {
			var cell = queue.poll();
			for (var direction : directions) {
				var nextX = cell[0];
				var nextY = cell[1];
				var nextDistance = minDistances[nextX][nextY];
				while (isValid(nextX + direction[0], nextY + direction[1], maze)
						&& maze[nextX + direction[0]][nextY + direction[1]] != 1) {
					nextX += direction[0];
					nextY += direction[1];
					nextDistance++;
				}
				// ! letting multiple paths flow through
				if (nextDistance < minDistances[nextX][nextY]) {
					minDistances[nextX][nextY] = nextDistance;
					queue.add(new int[] {nextX, nextY});
				}
			}
		}
		return minDistances[destination[0]][destination[1]] == Integer.MAX_VALUE
				? -1
				: minDistances[destination[0]][destination[1]];
	}

	public static void main(String[] args) {
		int[][] maze = {
			{0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0},
			{1, 1, 0, 1, 1},
			{0, 0, 0, 0, 0}
		};
		int[] start = {0, 4};
		int[] destination = {4, 4};
		System.out.println(new Maze2().shortestDistance(maze, start, destination)); // 12

		int[][] maze2 = {
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
