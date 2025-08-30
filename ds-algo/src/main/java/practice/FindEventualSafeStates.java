package practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/** [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) */
public class FindEventualSafeStates {
	public List<Integer> eventualSafeNodes(int[][] graph) {
		var visited = new int[graph.length];
		return IntStream.rangeClosed(0, graph.length - 1)
				.filter(node -> isSafeDFS(node, graph, visited))
				.boxed()
				.toList();
	}

	private static boolean isSafeDFS(int node, int[][] graph, int[] visited) {
		if (visited[node] != 0) {
			return visited[node] == 1;
		}
		visited[node] = 2; // ! visited, but not sure if safe, so mark unsafe
		var isSafe = Arrays.stream(graph[node]).allMatch(v -> isSafeDFS(v, graph, visited));
		if (isSafe) {
			visited[node] = 1; // ! Mark Safe
		}
		return isSafe;
	}

	static void main() {
		var fs = new FindEventualSafeStates();
		System.out.println(fs.eventualSafeNodes(new int[][] {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
		System.out.println(
				fs.eventualSafeNodes(new int[][] {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}}));
	}
}
