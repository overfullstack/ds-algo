package gfg.regular.graph;

import ds.DiGraph;
import java.util.Scanner;
import java.util.Stack;

/** Created by gakshintala on 4/19/16. */
public class DFSWithoutRecursion {
	static void main() {
		var scn = new Scanner(System.in);
		var vertexCount = scn.nextInt();
		var edgeCount = scn.nextInt();

		var g = readGraph(scn, vertexCount, edgeCount);
		System.out.println("Normal DFS: ");
		dfsIterative(g);

		System.out.println();
		var visited = new boolean[vertexCount + 1];
		System.out.println("g4g.regular.backtracking DFS: ");
		dfsRecursive(g, 1, visited);
	}

	private static void dfsRecursive(DiGraph g, int vertex, boolean[] visited) {
		visited[vertex] = true;
		System.out.print(vertex + " ");
		for (int neighbour : g.getAllOutgoingVertices(vertex)) {
			if (!visited[neighbour]) {
				dfsRecursive(g, neighbour, visited);
			}
		}
	}

	private static void dfsIterative(DiGraph g) {
		var stk = new Stack<Integer>();
		var vertexCount = g.getVertexCount();
		var visited = new boolean[vertexCount + 1];
		stk.add(1);
		while (!stk.isEmpty()) {
			int vertex = stk.pop();
			visited[vertex] = true;
			System.out.print(vertex + " ");
			for (int neighbour : g.getAllOutgoingVertices(vertex)) {
				if (!visited[neighbour]) {
					stk.add(neighbour);
				}
			}
		}
	}

	private static DiGraph readGraph(Scanner scn, int vertexCount, int edgeCount) {
		var DiGraph = new DiGraph(vertexCount);
		for (var i = 0; i < edgeCount; i++) {
			DiGraph.addEdge(scn.nextInt(), scn.nextInt());
		}
		return DiGraph;
	}
}
