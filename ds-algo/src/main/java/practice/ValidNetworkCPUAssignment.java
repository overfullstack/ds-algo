package practice;

public class ValidNetworkCPUAssignment {

	private static final int MOD = 1_000_000_007;

	public int countValidAssignments(int serverNodes, int[] serverFrom, int[] serverTo) {
		// Build adjacency list representation of the graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < serverNodes; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < serverFrom.length; i++) {
			int from = serverFrom[i];
			int to = serverTo[i];
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		// Check if graph is bipartite (can be colored with 2 colors)
		// If not bipartite, no valid assignment exists
		if (!isBipartite(graph, serverNodes)) {
			return 0;
		}

		// Count connected components
		boolean[] visited = new boolean[serverNodes];
		int components = 0;

		for (int i = 0; i < serverNodes; i++) {
			if (!visited[i]) {
				components++;
				dfs(i, graph, visited);
			}
		}

		// For each component, we have 2 choices:
		// - Color 1: odd cores (1, 3) for one partition, even cores (2) for other
		// - Color 2: even cores (2) for one partition, odd cores (1, 3) for other
		// Each choice gives us 2^oddNodes × 2^evenNodes ways

		long result = 1;
		for (int i = 0; i < components; i++) {
			result = (result * 2) % MOD; // 2 choices per component
		}

		return (int) result;
	}

	private boolean isBipartite(List<List<Integer>> graph, int nodes) {
		int[] colors = new int[nodes]; // 0 = uncolored, 1 = color1, 2 = color2

		for (int i = 0; i < nodes; i++) {
			if (colors[i] == 0) {
				if (!canColorBipartite(i, 1, colors, graph)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean canColorBipartite(int node, int color, int[] colors, List<List<Integer>> graph) {
		colors[node] = color;

		for (int neighbor : graph.get(node)) {
			if (colors[neighbor] == 0) {
				// Assign opposite color to neighbor
				if (!canColorBipartite(neighbor, 3 - color, colors, graph)) {
					return false;
				}
			} else if (colors[neighbor] == color) {
				// Same color on adjacent nodes - not bipartite
				return false;
			}
		}
		return true;
	}

	private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
		visited[node] = true;
		for (int neighbor : graph.get(node)) {
			if (!visited[neighbor]) {
				dfs(neighbor, graph, visited);
			}
		}
	}

	public static void main(String[] args) {
		ValidNetworkCPUAssignment solution = new ValidNetworkCPUAssignment();

		// Example 1: Simple line graph
		// 0 -- 1 -- 2
		// Valid assignments: 2 ways
		// Option 1: 0=odd, 1=even, 2=odd
		// Option 2: 0=even, 1=odd, 2=even
		System.out.println(
				solution.countValidAssignments(3, new int[] {0, 1}, new int[] {1, 2})); // Expected: 2

		// Example 2: Triangle graph (not bipartite)
		// 0 -- 1
		// | |
		// 2 -- 3
		// No valid assignment possible
		System.out.println(
				solution.countValidAssignments(
						4, new int[] {0, 1, 2, 3, 0, 2}, new int[] {1, 2, 3, 0, 2, 0})); // Expected: 0

		// Example 3: Disconnected components
		// Component 1: 0 -- 1
		// Component 2: 2 -- 3
		// Each component has 2 choices, total = 2 × 2 = 4
		System.out.println(
				solution.countValidAssignments(4, new int[] {0, 2}, new int[] {1, 3})); // Expected: 4
	}
}
