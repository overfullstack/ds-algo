package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Created by gakshintala on 1/10/16. */
public class TopologicalSort {

	private static final List<Integer> reversePostOrder = new ArrayList<>();
	private static boolean[] visited;

	static void main() {
		var scn = new Scanner(System.in);
		var vertexCount = scn.nextInt();
		var edgeCount = scn.nextInt();
		var graph = new Digraph(vertexCount);
		while (edgeCount-- > 0) graph.addEdge(scn.nextInt(), scn.nextInt());

		visited = new boolean[vertexCount + 1];

		for (var i = 1; i <= vertexCount; i++) {
			if (!visited[i]) dfs(graph, i);
		}
		System.out.println(reversePostOrder);
	}

	private static void dfs(Digraph graph, int vertex) {
		visited[vertex] = true;

		for (int adj : graph.getConnectingNodes(vertex)) {
			if (!visited[adj]) {
				dfs(graph, adj);
			}
		}
		reversePostOrder.add(vertex);
	}
}

class Digraph {
	private final int n;
	private final List<Integer>[] adj;

	Digraph(int n) {
		this.n = n;
		this.adj = new List[n + 1];

		for (var i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public void addEdge(int u, int v) {
		adj[u].add(v);
	}

	public List<Integer> getConnectingNodes(int source) {
		return adj[source];
	}
}
