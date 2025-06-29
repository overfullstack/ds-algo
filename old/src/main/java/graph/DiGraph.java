package graph;

/** Created by gakshintala on 12/3/15. */
public class DiGraph extends Graph {
	DiGraph(int vertexCount) {
		super(vertexCount);
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}
}
