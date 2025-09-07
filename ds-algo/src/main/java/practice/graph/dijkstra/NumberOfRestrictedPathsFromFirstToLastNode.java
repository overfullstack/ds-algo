package practice.graph.dijkstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/* 07 Sep 2025 08:54 */

/**
 * [1786. Number of Restricted Paths From First to Last
 * Node](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/)
 */
public class NumberOfRestrictedPathsFromFirstToLastNode {
	private static final int MOD = 1_000_000_007;

	public int countRestrictedPaths(int n, int[][] edges) {
		var graph = new HashMap<Integer, Map<Integer, Integer>>();
		for (var edge : edges) {
			graph.computeIfAbsent(edge[0], _ -> new HashMap<>()).put(edge[1], edge[2]);
			graph.computeIfAbsent(edge[1], _ -> new HashMap<>()).put(edge[0], edge[2]);
		}
		var pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(node -> node[1]));
		pq.add(new int[] {n, 0});
		var waysCountFromN = new int[n + 1];
    var minDistanceFromN = new int[n + 1];
    Arrays.fill(minDistanceFromN, Integer.MAX_VALUE);
    // ! Critical initializations
    waysCountFromN[n] = 1;
		minDistanceFromN[n] = 0;
		while (!pq.isEmpty()) {
			var node = pq.poll();
			var nodeIdx = node[0];
			var uDistanceFromN = node[1];
			if (uDistanceFromN <= minDistanceFromN[nodeIdx]) {
				for (var v : graph.getOrDefault(nodeIdx, Collections.emptyMap()).entrySet()) {
					var nextDistanceFromN = v.getValue() + uDistanceFromN;
					if (nextDistanceFromN < minDistanceFromN[v.getKey()]) {
						// ! Update `minDistanceFromN` on enqueue to keep it current and immediately compare
						minDistanceFromN[v.getKey()] = nextDistanceFromN;
						pq.add(new int[] {v.getKey(), nextDistanceFromN});
					}
					// ! Neighbour closer to N found, add-up ways
					if (uDistanceFromN > minDistanceFromN[v.getKey()]) {
						waysCountFromN[nodeIdx] = (waysCountFromN[nodeIdx] + waysCountFromN[v.getKey()]) % MOD;
					}
				}
			}
		}
		return waysCountFromN[1];
	}

	private static int dfsTopDown(
			final int n,
			final int[] minDistanceFromN,
			final HashMap<Integer, Map<Integer, Integer>> graph) {
		var dp = new int[n + 1];
		dp[n] = 1;
		var sortedNodesByMinDistanceFromN =
				IntStream.rangeClosed(1, n)
						.boxed()
						.sorted(Comparator.comparingInt(a -> minDistanceFromN[a]))
						.toList();

		// ! By iterating through the nodes in this sorted order, we ensure that when we calculate the
		// ! paths for a node u, we have already computed the paths for all its neighbors v that are
		// ! closer to n (i.e., distance[v] < distance[u]).
		for (var u : sortedNodesByMinDistanceFromN) { // ! First node shall always be `n`
			for (var neighbor : graph.getOrDefault(u, Collections.emptyMap()).entrySet()) {
				int v = neighbor.getKey();
				if (minDistanceFromN[u] > minDistanceFromN[v]) {
					dp[u] = (dp[u] + dp[v]) % 1000000007;
				}
			}
		}

		return dp[1];
	}

	// ! Using this gives ⏰TLE
	private int dfsWithMemo(
			int src,
			int n,
			int[] minDistanceFromN,
			Map<Integer, Map<Integer, Integer>> graph,
			int[] memo) {
		if (src == n) {
			return 1;
		}
		if (memo[src] != 0) {
			return memo[src];
		}
		var result = 0;
		for (var neighbor : graph.getOrDefault(src, Collections.emptyMap()).keySet()) {
			if (minDistanceFromN[src] > minDistanceFromN[neighbor]) {
				result = (result + dfsWithMemo(neighbor, n, minDistanceFromN, graph, memo)) % 1000000007;
			}
		}
		memo[src] = result;
		return result;
	}

	static void main() {
		var obj = new NumberOfRestrictedPathsFromFirstToLastNode();
		int n = 5;
		int[][] edges = {
			{1, 2, 3},
			{1, 3, 3},
			{2, 3, 1},
			{1, 4, 2},
			{5, 2, 2},
			{3, 5, 1},
			{5, 4, 10}
		};
		System.out.println(obj.countRestrictedPaths(n, edges)); // 3
		n = 7;
		edges =
				new int[][] {
					{1, 3, 1},
					{4, 1, 2},
					{7, 3, 4},
					{2, 5, 3},
					{5, 6, 1},
					{6, 7, 2},
					{7, 5, 3},
					{2, 6, 4}
				};
		System.out.println(obj.countRestrictedPaths(n, edges)); // 1
	}
}
