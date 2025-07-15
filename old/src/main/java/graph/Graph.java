package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/** Created by gakshintala on 11/20/15. */
public class Graph {
	int vertexCount;
	List<Integer>[] adj;

	Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		adj = new LinkedList[vertexCount];
		for (var i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public static Graph readGraph() {
		var scn = new Scanner(System.in);

		System.out.println("Enter Edges and Vertices:");
		var e = scn.nextInt();
		var v = scn.nextInt();

		var g = new Graph(v);

		System.out.println("Enter Connections:");
		for (var i = 0; i < e; i++) {
			g.addEdge(scn.nextInt(), scn.nextInt());
		}

		return g;
	}

	public static void main(String[] args) {
		var g = readGraph();
		g.printGraph();
		System.out.println("Enter Source and Vertex to Find:");
		var scn = new Scanner(System.in);

		if (g.areTheyConnectedBfs(scn.nextInt(), scn.nextInt())) System.out.println("Connected!!!");
		else System.out.println("Not Connected!!!");
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	public Iterable<Integer> getConnectedNodes(int v) {
		return adj[v];
	}

	public void printGraph() {
		for (var v = 0; v < this.vertexCount; v++)
			for (int w : this.getConnectedNodes(v)) System.out.println(v + " - " + w);
	}

	public boolean areTheyConnectedDfs(int source, int vertexToFind, boolean[] visited) {
		if (source == vertexToFind) {
			return true;
		}
		if (visited[source]) return false;
		visited[source] = true;
		for (int adjNode : this.getConnectedNodes(source)) {
			if (areTheyConnectedDfs(adjNode, vertexToFind, visited)) break;
		}
		return false;
	}

	public boolean areTheyConnectedBfs(int source, int vertexToFind) {
		Queue<Integer> queue = new LinkedList<>();
		var visited = new boolean[this.vertexCount];
		queue.add(source);
		visited[source] = true;
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			if (vertex == vertexToFind) return true;
			for (int e : this.getConnectedNodes(vertex))
				if (!visited[e]) {
					queue.add(e);
					visited[e] = true;
				}
		}
		return false;
	}
}
