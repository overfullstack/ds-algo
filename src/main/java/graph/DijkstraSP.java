package graph;

import java.util.PriorityQueue;

/**
 * Created by gakshintala on 1/6/16.
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Integer> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int source) {
        var vertexCount = graph.vertexCount();
        edgeTo = new DirectedEdge[vertexCount];
        distTo = new double[vertexCount];
        pq = new PriorityQueue<>();

        for (var i = 0; i < vertexCount; i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[source] = 0;
        pq.add(source);

        while (!pq.isEmpty()) {
            int vertex = pq.poll();
            for (var edge : graph.getAllReachableNodes(vertex)) {
                relax(edge);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        var from = edge.from();
        var to = edge.to();

        if (distTo[to] > distTo[from] + edge.weight()) {
            distTo[to] = distTo[from] + edge.weight();
            edgeTo[to] = edge;
        }

        if (!pq.contains(to)) {
            pq.add(to);
        }
    }
}