package gfg.regular.graph;

import java.util.Arrays;

/** Created by gakshintala on 4/20/16. */
public class HamiltonianCycle {
	static void main() {

		// Graph is represented as Adjacency Matrix
		int[][] graph1 = {
			{0, 1, 0, 1, 0}, {1, 0, 1, 1, 1}, {0, 1, 0, 0, 1}, {1, 1, 0, 0, 1}, {0, 1, 1, 1, 0},
		};
		hamCycle(graph1);

		int[][] graph2 = {
			{0, 1, 0, 1, 0}, {1, 0, 1, 1, 1}, {0, 1, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 1, 1, 0, 0},
		};
		hamCycle(graph2);
	}

	private static void hamCycle(int[][] graph) {
		// Start Vertex is 0
		int[] path = {0, -1, -1, -1, -1};

		// Checking for nextRight pos 1
		if (hasHamCycle(graph, path, 1)) {
			System.out.println(Arrays.toString(path));
		} else {
			System.out.println("No Hamilton Exists");
		}
	}

	private static boolean hasHamCycle(int[][] graph, int[] path, int nextPos) {
		if (nextPos == 5) { // Crossed last vertex, Check if last vertex is connected to first
			return graph[path[nextPos - 1]][0] == 1;
		}

		// Looping through all the neighbours of current vertex path[nextPos-1]
		for (var i = 1; i < 5; i++) {
			// Checking if neighbour is safe to be added to the path
			if (isSafe(graph, path, nextPos - 1, i)) {
				// If safe, add neighbour to path
				path[nextPos] = i;
				if (hasHamCycle(graph, path, nextPos + 1)) {
					return true;
				}
				// Backtrack if no HamCycle
				path[nextPos] = -1;
			}
		}
		return false;
	}

	private static boolean isSafe(int[][] graph, int[] path, int pos, int neighbour) {
		// If neighbour not connected to current vertex
		if (graph[path[pos]][neighbour] == 0) {
			return false;
		}

		// If the neighbour is already present in the path
		for (var p : path) {
			if (p == neighbour) {
				return false;
			}
		}

		return true;
	}
}
