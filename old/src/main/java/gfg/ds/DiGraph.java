package gfg.ds;

import java.util.LinkedList;
import java.util.List;

/** Created by gakshintala on 4/19/16. */
public class DiGraph {
  private int vertexCount;
  private List<Integer>[] adj;

  public DiGraph(int vertexCount) {
    this.vertexCount = vertexCount;
    adj = new LinkedList[vertexCount + 1];
    for (var i = 1; i <= vertexCount; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  public int getVertexCount() {
    return this.vertexCount;
  }

  public void addEdge(int a, int b) {
    adj[a].add(b);
  }

  public Iterable<Integer> getAllOutgoingVertices(int vertex) {
    return adj[vertex];
  }
}
