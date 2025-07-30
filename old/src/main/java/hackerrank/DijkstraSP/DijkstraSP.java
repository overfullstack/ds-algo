package hackerrank.DijkstraSP;

import hackerrank.DS.DirectedEdge;
import hackerrank.DS.EdgeWeightedDigraph;
import hackerrank.DS.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/** Created by gakshintala on 1/6/16. */
public class DijkstraSP {
	private static EdgeWeightedDigraph graph;
	private static DirectedEdge[] edgeTo; // Edge to TO from FROM
	private static PriorityQueue<Vertex> pq;

	static void main() {
		var scn = new Scanner(System.in);
		var testCases = scn.nextInt();
		while (testCases-- > 0) {
			var vertexCount = scn.nextInt();
			var edgeCount = scn.nextInt();

			graph = new EdgeWeightedDigraph(vertexCount);
			edgeTo = new DirectedEdge[vertexCount + 1];

			while (edgeCount-- > 0) {
				graph.addEdge(scn.nextInt(), scn.nextInt(), scn.nextInt());
			}
			var source = scn.nextInt();
			graph = findShortDistancesToAllNodes(source);

			printShortestDistances(source);
			printShortestPaths(source);
		}
	}

	private static EdgeWeightedDigraph findShortDistancesToAllNodes(int source) {
		pq = new PriorityQueue<>();
		graph.getVertices()[source].distFromSource = 0;
		edgeTo[source] = null;
		pq.add(graph.getVertices()[source]);
		while (!pq.isEmpty()) {
			var fromVertex = pq.poll();
			for (var edge : graph.getConnectingEdges(fromVertex.index)) {
				relax(edge);
			}
		}
		return graph;
	}

	private static void relax(DirectedEdge edge) {
		var from = graph.getVertices()[edge.from()];
		var to = graph.getVertices()[edge.to()];
		var distanceFromSourceViaFrom = from.distFromSource + edge.weight();
		if (distanceFromSourceViaFrom < to.distFromSource) {
			to.distFromSource = distanceFromSourceViaFrom;
			edgeTo[to.index] = edge;
			pq.add(to);
		}
	}

	private static void printShortestPathForVertex(int vertexIndex) {
		List<DirectedEdge> path = new ArrayList<>();
		for (var edge = edgeTo[vertexIndex]; edge != null; edge = edgeTo[edge.from()]) {
			path.add(edge);
		}
		Collections.reverse(path);
		System.out.println(path);
	}

	private static void printShortestDistances(int source) {
		var vertexCount = graph.vertexCount();
		var result = new StringBuilder();
		for (var i = 1; i <= vertexCount; i++) {
			if (i != source) {
				result.append(graph.getVertices()[i].distFromSource).append(" ");
			}
			System.out.println(result);
		}
	}

	private static void printShortestPaths(int source) {
		var vertexCount = graph.vertexCount();
		for (var i = 1; i <= vertexCount; i++) {
			if (i == source) continue;
			printShortestPathForVertex(i);
		}
	}
}
