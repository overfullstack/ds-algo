package graph;

import java.util.LinkedList;

/**
 * Created by gakshintala on 12/15/15.
 */
public class EdgeWeightedGraph {
    int N;
    LinkedList<Edge>[] adj;

    EdgeWeightedGraph(int n) {
        this.N = n;
        adj = new LinkedList[n];
        for (var adjList : adj)
            adjList = new LinkedList<>();
    }

    public void addEdge(Edge e) {
        var v = e.either();
        var w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> getAllConnectedNodes(int v) {
        return adj[v];
    }
}

class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == this.v) return this.w;
        return v;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
