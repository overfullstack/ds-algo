package hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/** Created by gakshintala on 12/16/15. */
public class MSTAlgos {

  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var vertexCount = scn.nextInt();
    var edgeCount = scn.nextInt();

    var edgeWeightedGraph = readGraph(vertexCount, edgeCount, scn);

    System.out.println(krusalsAlgo(edgeWeightedGraph, vertexCount, edgeCount));
    // int startVertex = scn.nextInt();
    // System.out.println(primsLazyAlgo(edgeWeightedGraph,vertexCount,edgeCount,startVertex));
  }

  private static int primsLazyAlgo(
      EdgeWeightedGraphMST edgeWeightedGraph, int vertexCount, int edgeCount, int startVertex) {
    var edgePQ = new PriorityQueue<EdgeMST>(edgeWeightedGraph.getAllConnectedEdges(startVertex));
    var visited = new boolean[vertexCount + 1];
    addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, startVertex, visited);
    List<EdgeMST> mst = new ArrayList<>();
    while (!edgePQ.isEmpty()) {
      var e = edgePQ.poll();
      var v = e.either();
      var w = e.other(v);
      if (!visited[v] || !visited[w]) {
        mst.add(e);
        if (!visited[v]) addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, v, visited);
        if (!visited[w]) addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, w, visited);
      }
    }
    return sumOfEdgeWeights(mst);
  }

  private static void addEdgesWithUnvisitedVerticesToPQ(
      EdgeWeightedGraphMST edgeWeightedGraph,
      PriorityQueue<EdgeMST> edgePQ,
      int v,
      boolean[] visited) {
    visited[v] = true;
    for (var e : edgeWeightedGraph.getAllConnectedEdges(v)) if (!visited[e.other(v)]) edgePQ.add(e);
  }

  private static int krusalsAlgo(
      EdgeWeightedGraphMST edgeWeightedGraph, int vertexCount, int edgeCount) {
    var edgePQ = new PriorityQueue<EdgeMST>(edgeWeightedGraph.getAllEdges());
    List<EdgeMST> mst = new ArrayList<>();
    var union = new QuickUnion(vertexCount);
    // Pick the smallest edge from PQ
    while (!edgePQ.isEmpty() && mst.size() < vertexCount - 1) {
      var e = edgePQ.poll();
      var v = e.either();
      var w = e.other(v);
      // Check if v,w are connected before using Quick Union, to detect if there is a cycle
      if (!union.connected(v, w)) { // To check if it creates a cycle
        union.union(v, w);
        mst.add(e);
      }
    }
    return sumOfEdgeWeights(mst);
  }

  private static int sumOfEdgeWeights(List<EdgeMST> mst) {
    var sum = 0;
    for (var e : mst) sum += e.weight();
    return sum;
  }

  private static EdgeWeightedGraphMST readGraph(int vertexCount, int edgeCount, Scanner scn) {
    var edg = new EdgeWeightedGraphMST(vertexCount);
    while (edgeCount-- > 0) edg.addEdge(new EdgeMST(scn.nextInt(), scn.nextInt(), scn.nextInt()));
    return edg;
  }
}

class EdgeWeightedGraphMST {
  private final int n;
  private final LinkedList<EdgeMST>[] adj;
  private final HashSet<EdgeMST> edges = new HashSet<>();

  EdgeWeightedGraphMST(int n) {
    this.n = n;
    this.adj = new LinkedList[n + 1];
    for (var i = 1; i <= n; i++) adj[i] = new LinkedList<>();
  }

  public int getVertexCount() {
    return this.n;
  }

  public void addEdge(EdgeMST e) {
    edges.add(e);
    var v = e.either();
    var w = e.other(v);
    adj[v].add(e);
    adj[w].add(e);
  }

  public LinkedList<EdgeMST> getAllConnectedEdges(int v) {
    return adj[v];
  }

  public Set<EdgeMST> getAllEdges() {
    return edges;
  }
}

class EdgeMST implements Comparable<EdgeMST> {
  private final int v, w;
  private final int weight;

  EdgeMST(int v, int w, int weight) {
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

  public int weight() {
    return this.weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EdgeMST edgeMst = (EdgeMST) o;
    return v == edgeMst.v && w == edgeMst.w && weight == edgeMst.weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(v, w, weight);
  }

  @Override
  public int compareTo(EdgeMST that) {
    return Integer.compare(this.weight, that.weight);
  }
}

class QuickUnion {
  int[] id;

  QuickUnion(int n) {
    id = new int[n + 1];
    for (var i = 1; i <= n; i++) id[i] = i;
  }

  private int root(int i) {
    while (i != id[i]) i = id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    id[root(p)] = root(q);
  }
}
