package practice.graph;

import java.util.Arrays;
import java.util.stream.IntStream;

/* 02 Sep 2025 16:08 */

/** [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/) */
public class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
		var colors = new int[graph.length];
		return IntStream.rangeClosed(0, graph.length - 1)
				.filter(n -> colors[n] == 0)
				.allMatch(n -> isGraphBipartite(n, 1, colors, graph));
	}

	private static boolean isGraphBipartite(int node, int colorToSet, int[] colors, int[][] graph) {
		if (colors[node] != 0) {
			return colors[node] == colorToSet;
		}
		colors[node] = colorToSet;
		return Arrays.stream(graph[node])
				.allMatch(v -> isGraphBipartite(v, -colorToSet, colors, graph));
	}

	static void main() {
		var graph = new int[][] {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
		var isGraphBipartite = new IsGraphBipartite();
		System.out.println(isGraphBipartite.isBipartite(graph));

		int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
		System.out.println(isGraphBipartite.isBipartite(graph2));
	}
}
