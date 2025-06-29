package hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

/** Created by gakshintala on 12/4/15. */
public class ShortestReach {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		for (var c = 0; c < testCases; c++) {
			var vertexCount = scn.nextInt();
			var edgeCount = scn.nextInt();
			var graph = readGraph(scn, vertexCount, edgeCount);

			var visited = new boolean[vertexCount + 1];
			Queue<Integer> queue = new LinkedList<>();
			Map<Integer, Integer> distanceFromSource = new TreeMap<>();

			var source = scn.nextInt();
			queue.add(source);
			visited[source] = true;
			distanceFromSource.put(source, 0);

			while (!queue.isEmpty()) {
				int vertex = queue.poll();
				var distanceFromSourceViaNeighbour = distanceFromSource.get(vertex) + 6;
				for (int connectedVertex : graph.getAllConnectedVertices(vertex)) {
					if (distanceFromSource.get(connectedVertex) == null
							|| distanceFromSource.get(connectedVertex) > distanceFromSourceViaNeighbour) {
						distanceFromSource.put(connectedVertex, distanceFromSourceViaNeighbour);
						if (!visited[connectedVertex]) {
							queue.add(connectedVertex);
							visited[connectedVertex] = true;
						}
					}
				}
			}

			for (var i = 1; i <= vertexCount; i++) {
				if (i == source) continue;
				var d = distanceFromSource.get(i);
				var distance = d != null ? d : -1;
				System.out.print(distance + " ");
			}
			System.out.println();
		}
	}

	private static Graph_SR readGraph(Scanner scn, int vertexCount, int edgeCount) {
		var graph = new Graph_SR(vertexCount);
		for (var i = 0; i < edgeCount; i++) {
			graph.addEdge(scn.nextInt(), scn.nextInt());
		}
		return graph;
	}
}

class Graph_SR {
	private List<Integer>[] adj;

	Graph_SR(int vertexCount) {
		adj = new LinkedList[vertexCount + 1];

		for (var i = 1; i <= vertexCount; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int a, int b) {
		adj[a].add(b);
		adj[b].add(a);
	}

	public Iterable<Integer> getAllConnectedVertices(int vertex) {
		return adj[vertex];
	}
}
