package practice.unionfind;

/* 04 Oct 2025 17:46 */

import java.util.Arrays;
import java.util.List;

/** [434 · Number of Islands II](https://www.lintcode.com/problem/434/) */
public class NumberOfIslands2 {
	public List<Integer> numIslands2(int n, int m, Point[] operators) {
		var uf = new UnionFind(n, m);
		var grid = new int[n][m];
		return Arrays.stream(operators)
				.map(
						operator -> {
							if (grid[operator.x][operator.y] == 1) {
								return uf.islandCount;
							}
							uf.islandCount++;
							grid[operator.x][operator.y] = 1;
							Arrays.stream(directions)
									.map(d -> new int[] {operator.x + d[0], operator.y + d[1]})
									.filter(c -> isValid(c, n, m) && grid[c[0]][c[1]] == 1)
									.forEach(c -> uf.union(new int[] {operator.x, operator.y}, c));
							return uf.islandCount;
						})
				.toList();
	}

	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	private static class UnionFind {
		private final int cols;
		private final int[] roots;
		private final int[] ranks;
		private int islandCount;

		private UnionFind(int n, int m) {
			cols = m;
			var cellCount = n * m;
			roots = new int[cellCount];
			for (var i = 0; i < cellCount; i++) {
				roots[i] = i;
			}
			ranks = new int[cellCount];
			islandCount = 0;
		}

		private int find(int x) {
			while (roots[x] != x) {
				x = roots[x];
			}
			return x;
		}

		private void union(int[] n1, int[] n2) {
			union(n1[0] * cols + n1[1], n2[0] * cols + n2[1]);
		}

		private void union(int n1, int n2) {
			var root1 = find(n1);
			var root2 = find(n2);
			if (root1 != root2) {
				if (ranks[root1] < ranks[root2]) {
					roots[root1] = root2;
				} else if (ranks[root1] > ranks[root2]) {
					roots[root2] = root1;
				} else {
					roots[root2] = root1;
					ranks[root1]++;
				}
				islandCount--;
			}
		}
	}

	private static boolean isValid(int[] cell, int n, int m) {
		return cell[0] >= 0 && cell[0] < n && cell[1] >= 0 && cell[1] < m;
	}

	private static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	static void main() {
		var obj = new NumberOfIslands2();
		System.out.println(
				obj.numIslands2(
						4,
						5,
						new Point[] {
							new Point(1, 1), new Point(0, 1), new Point(3, 3), new Point(3, 4)
						})); // [1,1,2,2]
		System.out.println(
				obj.numIslands2(
						3,
						3,
						new Point[] {
							new Point(0, 0), new Point(0, 1), new Point(2, 2), new Point(2, 1)
						})); // [1,1,2,2]
		System.out.println(
				obj.numIslands2(
						3,
						3,
						new Point[] {
							new Point(0, 0), new Point(0, 1), new Point(2, 2), new Point(2, 2)
						})); // [1,1,2,2]
	}
}
