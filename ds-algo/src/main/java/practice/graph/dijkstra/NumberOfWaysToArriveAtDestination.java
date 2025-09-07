package practice.graph.dijkstra;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/* 07 Sep 2025 13:06 */

/**
 * [1976. Number of Ways to Arrive at
 * Destination](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/)
 */
public class NumberOfWaysToArriveAtDestination {
	private static final int MOD = 1_000_000_007;

	public int countPaths(int n, int[][] roads) {
		var graph = new HashMap<Integer, Map<Integer, Integer>>();
		for (var road : roads) {
			graph.computeIfAbsent(road[0], _ -> new HashMap<>()).put(road[1], road[2]);
			graph.computeIfAbsent(road[1], _ -> new HashMap<>()).put(road[0], road[2]);
		}
		var pq = new PriorityQueue<>(Comparator.<long[]>comparingLong(node -> node[1]));
		var minDistanceFrom0 = new long[n];
		Arrays.fill(minDistanceFrom0, Long.MAX_VALUE);
		var countWaysFrom0 = new int[n];
		minDistanceFrom0[0] = 0;
		countWaysFrom0[0] = 1;
		pq.add(new long[] {0, 0});
		while (!pq.isEmpty()) {
			var node = pq.poll();
			var u = (int) node[0];
			var uDistanceFrom0 = node[1];
			if (uDistanceFrom0 <= minDistanceFrom0[u]) {
				for (var v : graph.getOrDefault(u, Collections.emptyMap()).entrySet()) {
					var nextDistanceFrom0 = v.getValue() + uDistanceFrom0;
					if (nextDistanceFrom0 < minDistanceFrom0[v.getKey()]) {
						minDistanceFrom0[v.getKey()] = nextDistanceFrom0;
						// ! Found a Shorter path - reset the count
						countWaysFrom0[v.getKey()] = countWaysFrom0[u];
						pq.add(new long[] {v.getKey(), nextDistanceFrom0});
						// ! else if, found another path from a `u` reaching with the same shortest path
						// ! This count is valid, until we find a shorter path
					} else if (nextDistanceFrom0 == minDistanceFrom0[v.getKey()]) {
						countWaysFrom0[v.getKey()] = (countWaysFrom0[v.getKey()] + countWaysFrom0[u]) % MOD;
					}
				}
			}
		}
		return countWaysFrom0[n - 1];
	}

	static void main() {
		var obj = new NumberOfWaysToArriveAtDestination();
		int[][] roads = {
			{0, 6, 7},
			{0, 1, 2},
			{1, 2, 3},
			{1, 3, 3},
			{6, 3, 3},
			{3, 5, 1},
			{6, 5, 1},
			{2, 5, 1},
			{0, 4, 5},
			{4, 6, 2}
		};
		System.out.println(obj.countPaths(7, roads)); // 4
		roads = new int[][] {{1, 0, 10}};
		System.out.println(obj.countPaths(2, roads)); // 1
		roads =
				new int[][] {
					{0, 1, 5},
					{0, 2, 1},
					{1, 3, 1},
					{1, 5, 1},
					{2, 3, 2},
					{2, 4, 1},
					{3, 4, 1}
				};
		System.out.println(obj.countPaths(6, roads)); // 2
	}
}
