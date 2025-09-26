package tbd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendNetwork {

	public long maxProductInLargestGroup(int friends_nodes, int[] x, int[] y, int[] c) {
		// Group edges by company - each company will have its own set of connections
		Map<Integer, List<int[]>> companyEdges = new HashMap<>();
		for (int i = 0; i < x.length; i++) {
			companyEdges.computeIfAbsent(c[i], k -> new ArrayList<>()).add(new int[] {x[i], y[i]});
		}

		// Find the largest connected component across all companies
		List<Integer> largestComponent = new ArrayList<>();

		// Process each company's network separately
		for (Map.Entry<Integer, List<int[]>> entry : companyEdges.entrySet()) {
			List<int[]> edges = entry.getValue();

			// Build adjacency list for this company's network
			Map<Integer, List<Integer>> graph = new HashMap<>();
			Set<Integer> nodes = new HashSet<>();

			for (int[] edge : edges) {
				int friend1 = edge[0];
				int friend2 = edge[1];

				graph.computeIfAbsent(friend1, k -> new ArrayList<>()).add(friend2);
				graph.computeIfAbsent(friend2, k -> new ArrayList<>()).add(friend1);
				nodes.add(friend1);
				nodes.add(friend2);
			}

			// Find all connected components for this company using DFS
			Set<Integer> visited = new HashSet<>();

			for (int node : nodes) {
				if (!visited.contains(node)) {
					List<Integer> component = new ArrayList<>();
					dfs(node, graph, visited, component);

					// Update largest component if this one is bigger
					if (component.size() > largestComponent.size()) {
						largestComponent = component;
					}
				}
			}
		}

		// Find the two largest friend IDs in the largest component
		// Sort in descending order to get the two largest values
		largestComponent.sort(Collections.reverseOrder());

		// The maximum product will be the product of the two largest IDs
		return (long) largestComponent.get(0) * largestComponent.get(1);
	}

	private void dfs(
			int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, List<Integer> component) {
		visited.add(node);
		component.add(node);

		// Visit all neighbors that haven't been visited
		if (graph.containsKey(node)) {
			for (int neighbor : graph.get(node)) {
				if (!visited.contains(neighbor)) {
					dfs(neighbor, graph, visited, component);
				}
			}
		}
	}
}
