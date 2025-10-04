package practice.unionfind;

import java.util.Arrays;

/* 26 Sep 2025 21:07 */

/**
 * [2316. Count Unreachable Pairs of Nodes in an Undirected
 * Graph](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/)
 */
public class CountUnreachablePairsOfNodesInAnUndirectedGraph {
	public long countPairs(int n, int[][] edges) {
		var uf = new UnionFind(n);
		for (var edge : edges) {
			uf.union(edge[0], edge[1]);
		}
		var groupCounts =
				Arrays.stream(uf.roots)
						.map(uf::find)
						.distinct()
						.mapToLong(root -> uf.ranks[root])
						.toArray();
		var result = 0L;
		var remainingNodes = (long) n;
		for (var groupCount : groupCounts) {
			remainingNodes -= groupCount;
			result += groupCount * remainingNodes;
		}
		return result;
	}

	private static class UnionFind {
		private final int[] roots;
		private final int[] ranks;

		private UnionFind(int n) {
			roots = new int[n];
			for (var i = 0; i < n; i++) {
				roots[i] = i;
			}
			ranks = new int[n];
			Arrays.fill(ranks, 1);
		}

		int find(int n) {
			while (n != roots[n]) {
				n = roots[n];
			}
			return n;
		}

		void union(int x, int y) {
			var rootX = find(x);
			var rootY = find(y);
			if (rootX != rootY) {
				if (ranks[rootX] > ranks[rootY]) {
					roots[rootY] = rootX;
					ranks[rootX] += ranks[rootY];
				} else {
					roots[rootX] = rootY;
					ranks[rootY] += ranks[rootX];
				}
			}
		}
	}

	static void main() {
		var c = new CountUnreachablePairsOfNodesInAnUndirectedGraph();
		System.out.println(
				c.countPairs(
						11,
						new int[][] {
							{5, 0}, {1, 0}, {10, 7}, {9, 8}, {7, 2}, {1, 3}, {0, 2}, {8, 5}, {4, 6}, {4, 2}
						}));
		System.out.println(c.countPairs(7, new int[][] {{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}})); // 14
		System.out.println(c.countPairs(3, new int[][] {{0, 1}, {0, 2}, {1, 2}})); // 0
	}
}
