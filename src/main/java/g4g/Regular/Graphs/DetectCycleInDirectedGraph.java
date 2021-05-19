package g4g.Regular.Graphs;

import g4g.DsAndUtils.DiGraph;
import java.util.Scanner;

/** Created by gakshintala on 3/28/16. */
public class DetectCycleInDirectedGraph {

  private static boolean[]
      visited; // visited - used to prevent running into nodes that are already visited
  private static boolean[]
      hasCycle; // hasCycle - used to nodes that are hasCycle and they are set to false if all the
  // nodes
  // going away from this are check for hasCycle and is clear without cycle
  private static DiGraph g;

  public static void main(String[] args) {
    var scn = new Scanner(System.in);
    var vertexCount = scn.nextInt();
    var edgeCount = scn.nextInt();
    visited = new boolean[vertexCount + 1];
    hasCycle = new boolean[vertexCount + 1];

    g = readGraph(scn, vertexCount, edgeCount);

    // Let us say first vertex with value '1' is on border, it won't start a cycle. So need to check
    // paths starting
    // from all vertices to see which one of them can cause a cycle.
    for (var i = 1; i <= vertexCount; i++) {
      if (!visited[i] && isCyclePresent(i)) {
        System.out.println("Cycle Detected!!!");
        return;
      }
    }
    System.out.println("No Cycle");
  }

  // This uses DFS
  private static boolean isCyclePresent(int i) {
    visited[i] = true;
    hasCycle[i] = true; // We are starting to check for hasCycle
    for (int neighbor : g.getAllOutgoingVertices(i)) {
      // If not visited, then send that node for isCyclePresent. If it passes to have a cycle,
      // return true;
      if (!visited[neighbor] && isCyclePresent(neighbor)) {
        return true;
      }
      // If above check is skipped due to visited, we still have record of hasCycle and we shall
      // check as below.
      // It says a node with hasCycle true is encountered before giving clearance for this dfs
      // traversal
      // `visited` is common for all nodes
      // `hasCycle` is specific for a node as it is reset below. It only holds info about DFT of a
      // particular node.
      // if any hasCycle detected during the traversal of it's neighbors or their children, then
      // cycle detected.
      if (hasCycle[neighbor]) {
        return true;
      }
    }
    hasCycle[i] =
        false; // DFT of all the neighbors and their children complete. This node is clear, doesn't
    // cause any cycle due to its outgoing nodes;
    return false; // No cycle here
  }

  private static DiGraph readGraph(Scanner scn, int vertexCount, int edgeCount) {
    var graph = new DiGraph(vertexCount);
    for (var i = 0; i < edgeCount; i++) {
      graph.addEdge(scn.nextInt(), scn.nextInt());
    }
    return graph;
  }
}
