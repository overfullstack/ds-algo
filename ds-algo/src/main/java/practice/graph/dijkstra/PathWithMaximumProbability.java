package practice.graph.dijkstra;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/* 06 Sep 2025 20:36 */

/**
 * [1514. Path with Maximum
 * Probability](https://leetcode.com/problems/path-with-maximum-probability/)
 */
public class PathWithMaximumProbability {
	public double maxProbability(
			int n, int[][] edges, double[] succProb, int start_node, int end_node) {
		var graph = new HashMap<Integer, Map<Integer, Double>>();
		for (var i = 0; i < edges.length; i++) {
			graph.computeIfAbsent(edges[i][0], _ -> new HashMap<>()).put(edges[i][1], succProb[i]);
			graph.computeIfAbsent(edges[i][1], _ -> new HashMap<>()).put(edges[i][0], succProb[i]);
		}
		var maxHeap =
				new PriorityQueue<>(Comparator.<double[]>comparingDouble(node -> node[1]).reversed());
		maxHeap.add(new double[] {start_node, 1d});
		var maxProbability = new double[n];
		while (!maxHeap.isEmpty()) {
			var node = maxHeap.poll();
			var nodeIdx = node[0];
			var probability = node[1];
			if (nodeIdx == end_node) {
				return probability;
			}
			if (probability > maxProbability[(int) nodeIdx]) {
				maxProbability[(int) nodeIdx] = probability;
				for (var neighbor : graph.getOrDefault((int) nodeIdx, Collections.emptyMap()).entrySet()) {
					var probabilityFromToTo = probability * neighbor.getValue();
					if (probabilityFromToTo > maxProbability[neighbor.getKey()]) {
						maxHeap.add(new double[] {neighbor.getKey(), probabilityFromToTo});
					}
				}
			}
		}
		return 0d;
	}

	static void main() {
		var p = new PathWithMaximumProbability();
		System.out.println(
				p.maxProbability(
						3, new int[][] {{0, 1}, {1, 2}, {0, 2}}, new double[] {0.5, 0.5, 0.2}, 0, 2)); // 0.25
		System.out.println(
				p.maxProbability(
						3, new int[][] {{0, 1}, {1, 2}, {0, 2}}, new double[] {0.5, 0.5, 0.3}, 0, 2)); // 0.3
		System.out.println(p.maxProbability(3, new int[][] {{0, 1}}, new double[] {0.5}, 0, 2)); // 0.0
	}
}
