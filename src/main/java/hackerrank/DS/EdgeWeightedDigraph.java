package hackerrank.DS;

import java.util.LinkedList;

/**
 * Created by Gopala Akshintala on 11/5/16.
 */
public class EdgeWeightedDigraph {
    // Vertices and diNeighbours are parallel lists, one-to-one mapped
    private Vertex[] vertices;
    private DiNeighbour[] diNeighbours;
    private int vertexCount;

    public EdgeWeightedDigraph(int vertexCount) {
        this.vertexCount = vertexCount;
        diNeighbours = new DiNeighbour[vertexCount + 1];
        vertices = new Vertex[vertexCount + 1];
        for (var i = 1; i <= vertexCount; i++) {
            diNeighbours[i] = new DiNeighbour();
            vertices[i] = new Vertex(i);
        }
    }

    public void addEdge(int from, int to, int weight) {
        var edge = new DirectedEdge(from, to, weight);
        var reverseEdge = new DirectedEdge(to, from, weight);
        diNeighbours[from].add(edge);
        diNeighbours[to].add(reverseEdge);
    }

    public Iterable<DirectedEdge> getConnectingEdges(int vertexIndex) {
        return diNeighbours[vertexIndex];
    }

    public int vertexCount() {
        return this.vertexCount;
    }

    public Vertex[] getVertices() {
        return vertices;
    }
}

class DiNeighbour extends LinkedList<DirectedEdge> {
}
