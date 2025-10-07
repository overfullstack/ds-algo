package practice.graph.undirected;

import ds.util.Pair;
import ds.util.Triple;
import java.util.ArrayDeque;
import java.util.HashSet;

/* 07 Oct 2025 10:41 */

/**
 * [847. Shortest Path Visiting All
 * Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes/)
 */
public class ShortestPathVisitingAllNodes {
	public int shortestPathLength(int[][] graph) { // * BFS
		var queue = new ArrayDeque<Triple<Integer, Integer, Integer>>();
		var visited = new HashSet<Pair<Integer, Integer>>();
		for (var node = 0; node < graph.length; node++) {
			var bitMask = (1 << node);
			queue.add(Triple.of(node, bitMask, 0));
			visited.add(Pair.of(node, bitMask));
		}
		var allVisitedBitMask = (1 << graph.length) - 1;
		while (!queue.isEmpty()) { // ! Unit weights, so no Dijkstra
			var node = queue.removeFirst();
			var u = node.first();
			var bitMask = node.second();
			var curCost = node.third();
			if (bitMask == allVisitedBitMask) {
				return curCost;
			}
			for (var v : graph[u]) {
				var nextNode = Pair.of(v, (bitMask | (1 << v)));
				// ! Prevent Cycles, visiting same node with the same node combination (bitMask) in the path
				// ! We cannot just block nodes, as we need to traverse the same node multiple times
				if (!visited.contains(nextNode)) {
					// ! Spl Visit-on-Enqueue as we have unweighted paths.
					// ! We won't have a scenario of having better paths later in the queue
					// ! It's more efficient as it avoids bloating `queue` with duplicate entries
					// ! It works here as it tracks a combo and not just `node`.
					visited.add(nextNode);
					queue.add(Triple.of(nextNode, curCost + 1));
				}
			}
		}
		return -1;
	}

	static void main() {
		var sol = new ShortestPathVisitingAllNodes();
		int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
		System.out.println(sol.shortestPathLength(graph)); // 4
		int[][] graph2 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};
		System.out.println(sol.shortestPathLength(graph2)); // 4
	}
}
