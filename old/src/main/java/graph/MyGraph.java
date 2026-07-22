package graph;

import java.util.HashSet;
import java.util.Set;

/** Created by gakshintala on 11/23/15. */
public class MyGraph {

  int V;
  Set<Integer>[] adj;

  MyGraph(int v) {
    this.V = v;
    this.adj = new Set[v];

    for (var i = 0; i < v; i++) {
      adj[i] = new HashSet<>();
    }
  }

  public void addEdge(int u, int v) {
    adj[u].add(v);
    adj[v].add(u);
  }

  public void printGraph(Graph g) {
    for (var v = 0; v < g.vertexCount; v++) {
      for (int w : adj[v]) {}
    }
  }
}
