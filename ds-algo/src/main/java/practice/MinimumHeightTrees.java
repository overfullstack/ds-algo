package practice;

/* 30 Aug 2025 07:59 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** [310. Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees) */
public class MinimumHeightTrees {

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n == 1) {
			return Collections.singletonList(0);
		}
		var graph = new HashMap<Integer, Set<Integer>>();
		for (var edge : edges) { // ! Undirected BiDiGraph
			graph.computeIfAbsent(edge[0], _ -> new HashSet<>()).add(edge[1]);
			graph.computeIfAbsent(edge[1], _ -> new HashSet<>()).add(edge[0]);
		}
    // * This is a kind of Topological traversal with BFS 
		var leaves =
				graph.entrySet().stream()
						.filter(entry -> entry.getValue().size() == 1)
						.map(Map.Entry::getKey)
						.toList();
		while (n > 2) {
			n -= leaves.size();
			var newLeaves = new ArrayList<Integer>();
			for (var leaf : leaves) {
				var onlyParent = graph.get(leaf).stream().findFirst().orElseThrow(); // Set of size 1
				graph.get(onlyParent).remove(leaf); // ! Disconnect leaf from parent
				if (graph.get(onlyParent).size() == 1) {
					newLeaves.add(onlyParent);
				}
			}
			leaves = newLeaves;
		}
		return leaves;
	}

	// ⏰ TLE
	public List<Integer> findMinHeightTreesDFS(int n, int[][] edges) {
		if (edges.length == 0) {
			return Collections.emptyList();
		}
		if (n == 1) {
			return List.of(0);
		}
		var graph = new HashMap<Integer, Set<Integer>>();
		for (var edge : edges) {
			graph.computeIfAbsent(edge[0], _ -> new HashSet<>()).add(edge[1]);
			graph.computeIfAbsent(edge[1], _ -> new HashSet<>()).add(edge[0]);
		}
		var maxLenToNodes =
				IntStream.rangeClosed(0, n - 1)
						.boxed()
						.collect(Collectors.groupingBy(node -> dfsMaxLen(node, new HashSet<>(), graph)));
		return maxLenToNodes.entrySet().stream()
				.min(Map.Entry.comparingByKey())
				.map(Map.Entry::getValue)
				.orElse(List.of());
	}

	private static int dfsMaxLen(int u, Set<Integer> visited, Map<Integer, Set<Integer>> graph) {
		visited.add(u);
		return 1
				+ graph.get(u).stream()
						.filter(v -> !visited.contains(v))
						.mapToInt(v -> dfsMaxLen(v, visited, graph))
						.max()
						.orElse(0);
	}

	static void main() {
		var minHeightTrees = new MinimumHeightTrees();
		System.out.println(minHeightTrees.findMinHeightTrees(4, new int[][] {{1, 0}, {1, 2}, {1, 3}}));
		System.out.println(
				minHeightTrees.findMinHeightTreesDFS(
						6, new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
	}
}
