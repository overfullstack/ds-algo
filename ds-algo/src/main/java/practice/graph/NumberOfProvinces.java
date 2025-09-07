package practice.graph;

import java.util.stream.IntStream;

/* 07 Sep 2025 16:49 */

/** [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/) */
public class NumberOfProvinces {
	public int findCircleNum(int[][] isConnected) {
		var visited = new boolean[isConnected.length];
		return (int)
				IntStream.range(0, isConnected.length) // ! Indices
						.filter(
								row -> {
									if (!visited[row]) {
										dfsPerGroup(isConnected, visited, row);
										return true;
									}
									return false;
								})
						.count();
	}

	private static void dfsPerGroup(int[][] isConnected, boolean[] visited, int row) {
		if (visited[row]) {
			return;
		}
		visited[row] = true;
		IntStream.range(0, isConnected[row].length) // ! Indices
				.filter(col -> isConnected[row][col] == 1)
				.forEach(col -> dfsPerGroup(isConnected, visited, col)); // ! Go to the connected city
	}

	static void main() {
		var obj = new NumberOfProvinces();
		System.out.println(obj.findCircleNum(new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // 2
		System.out.println(obj.findCircleNum(new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}})); // 3
		System.out.println(
				obj.findCircleNum(
						new int[][] {
							{1, 0, 0, 1},
							{0, 1, 1, 0},
							{0, 1, 1, 1},
							{1, 0, 1, 1}
						})); // 1
	}
}
