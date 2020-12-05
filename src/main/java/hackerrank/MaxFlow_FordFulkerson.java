package hackerrank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gakshintala on 1/13/16.
 */
public class MaxFlow_FordFulkerson {
    private FlowEdge[] edgeTo;
    private int maxFlowValue;

    public MaxFlow_FordFulkerson(FlowNetwork graph, int source, int destination) {
        this.maxFlowValue = 0;
        // Check all the paths possible from source to destination. 
        // Paths may have overlapping edges
        while (hasAugmentingPath(graph, source, destination)) {
            var bottleNeckCapacity = Integer.MAX_VALUE;

            // Find the bottleneck in the AugmentingPath path, which is Minimum residual capacity among all edges
            for (var v = source; v != destination; v = edgeTo[v].other(v))
                bottleNeckCapacity = Math.min(bottleNeckCapacity, edgeTo[v].getResidualCapacityTo(v));

            // Add the Bottleneck capacity to all the edges in the path
            for (var v = source; v != destination; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottleNeckCapacity);

            // This is the flow for this path, similarly loop for other paths as well.
            maxFlowValue += bottleNeckCapacity;
        }
    }

    public int getMaxFlowValue() {
        return maxFlowValue;
    }

    // It shall return different path based on the Residual Capacity 
    private boolean hasAugmentingPath(FlowNetwork graph, int source, int destination) {
        var visited = new boolean[graph.N];
        edgeTo = new FlowEdge[graph.N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (var adjEdge : graph.getNeighbours(v)) {
                var other = adjEdge.other(v);
                // Add to path only if there is any residual capacity left
                if (adjEdge.getResidualCapacityTo(other) > 0 && !visited[other]) {
                    edgeTo[other] = adjEdge;
                    if (other == destination) return true;
                    visited[other] = true;
                    queue.add(other);
                }
            }
        }
        return false;
    }
}

class FlowEdge {
    private final int from, to, capacity;
    private int flow;

    FlowEdge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int other(int v) {
        return (v == from ? to : from);
    }

    public int getResidualCapacityTo(int v) {
        if (v == to) return capacity - flow; // Forward edge
        else if (v == from) return flow; // Backward edge
        else throw new IllegalArgumentException("Undefined DijkstraSP.Vertex for the edge");
    }

    public void addResidualFlowTo(int v, int delta) {
        if (v == to) flow += delta; // Forward Edge
        else if (v == from) flow -= delta; // Backward Edge
    }
}

/**
 * Undirected graph
 */
class FlowNetwork {
    public int N;
    private LinkedList<FlowEdge>[] adj;

    FlowNetwork(int n) {
        this.N = n;
        adj = new LinkedList[n];

    }

    public void addEdge(FlowEdge e) {
        adj[e.from()].add(e);
        adj[e.to()].add(e);
    }

    public Iterable<FlowEdge> getNeighbours(int v) {
        return adj[v];
    }
}
