package g4g.regular.Graphs;

import g4g.ds.DiGraph;
import java.util.Scanner;

/**
 * Created by gakshintala on 3/28/16.
 *
 * <p>This can only calculate the length of first encountered Cycle.
 */
public class CountCycleLength {

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
      if (!visited[i]) {
        var CycleNode = checkCycle(i);
        if (CycleNode.nodeValue != -1) {
          System.out.println("Cycle Detected at: " + CycleNode.nodeValue);
          // int cycleLen = countCycleLength(CycleNode);
          System.out.println("Cycle length: " + CycleNode.count);
          // return;
        }
      }
    }
    System.out.println("No Cycle");
  }

  // This uses DFS
  private static CycleNode checkCycle(int i) {
    visited[i] = true;
    hasCycle[i] = true;
    for (int neighbour : g.getAllOutgoingVertices(i)) {
      if (!visited[neighbour]) {
        var result = checkCycle(neighbour);
        if (result.nodeValue == -1) {
          hasCycle[i] = false;
        } else if (result.countIncrement) {
          if (neighbour != result.nodeValue) {
            result.count++;
          } else {
            result.countIncrement = false;
          }
        }
        return result;
      }
      // If above check is skipped due to visited, we still have record of hasCycle and we shall
      // check as below.
      if (hasCycle[neighbour]) {
        return new CycleNode(neighbour, 1, true);
      }
    }
    hasCycle[i] = false; // This node is clear, doesn't cause any cycle due to its outgoing nodes;
    return new CycleNode(-1, 0, false); // No cycle here
  }

  private static DiGraph readGraph(Scanner scn, int vertexCount, int edgeCount) {
    var DiGraph = new DiGraph(vertexCount);
    for (var i = 0; i < edgeCount; i++) {
      DiGraph.addEdge(scn.nextInt(), scn.nextInt());
    }
    return DiGraph;
  }
}

class CycleNode {
  int nodeValue;
  int count;
  boolean countIncrement;

  public CycleNode(int nodeValue, int count, boolean countIncrement) {
    this.nodeValue = nodeValue;
    this.count = count;
    this.countIncrement = countIncrement;
  }
}
