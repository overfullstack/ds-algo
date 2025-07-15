package graph;

import java.util.LinkedList;

/** Created by gakshintala on 1/5/16. */
public class EdgeWeightedDigraph {
	LinkedList<DirectedEdge>[] adj;
	private int N;

	EdgeWeightedDigraph(int n) {
		this.N = n;
		adj = new LinkedList[n];
		for (var adjList : adj) adjList = new LinkedList<>();
	}

	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
	}

	public Iterable<DirectedEdge> getAllReachableNodes(int v) {
		return adj[v];
	}

	public int vertexCount() {
		return this.N;
	}

	public record DirectedEdge(int from, int to, double weight) {}
}
