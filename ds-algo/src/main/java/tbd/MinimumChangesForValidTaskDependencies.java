package tbd;

/** Hackerrank library */
public class MinimumChangesForValidTaskDependencies {

	public int minChanges(int[] taskDependency) {
		int n = taskDependency.length;

		// IMPORTANT: The input uses 1-indexed task numbers in the array values
		// taskDependency[0] = 2 means task 1 depends on task 2
		// Convert to 0-indexed for internal processing
		int[] dep = new int[n];
		for (int i = 0; i < n; i++) {
			dep[i] = taskDependency[i] - 1;
		}

		// In a functional graph (each node has exactly one outgoing edge),
		// each connected component has exactly one cycle

		boolean[] visited = new boolean[n];
		int components = 0;
		int selfLoops = 0;

		// Find all connected components and count self-loops
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				components++;
				// Traverse this component and mark all nodes as visited
				int current = i;
				while (!visited[current]) {
					visited[current] = true;
					current = dep[current];
				}
				// current is now in the cycle - check if it's a self-loop
				if (dep[current] == current) {
					selfLoops++;
				}
			}
		}

		// Calculate minimum changes:
		// 1. We need to merge all components into one: (components - 1) changes
		// 2. We need exactly one self-loop in the final structure

		int changes = 0;

		// First, connect all components into one
		if (components > 1) {
			changes += (components - 1);
		}

		// After merging, we need to ensure exactly one self-loop
		// When we merge components, we break one cycle per merge
		// So after merging, we have exactly 1 cycle remaining

		if (selfLoops == 0) {
			// No self-loops exist
			// The remaining cycle needs to be converted to a self-loop: 1 change
			changes += 1;
		} else if (selfLoops > 1) {
			// Multiple self-loops exist
			// When merging, we'll break (selfLoops - 1) of them
			// No additional changes needed beyond the merging
			// The merging already handles breaking the extra self-loops
		}
		// If selfLoops == 1, perfect! No additional changes needed

		return changes;
	}

	public static void main(String[] args) {
		MinimumChangesForValidTaskDependencies solution = new MinimumChangesForValidTaskDependencies();

		// Example from problem: [2, 3, 3, 4] (1-indexed values)
		// Task 1->2, Task 2->3, Task 3->3 (self), Task 4->4 (self)
		// Two components with two self-loops, need 1 change to merge
		System.out.println("Test 1: " + solution.minChanges(new int[] {2, 3, 3, 4})); // Expected: 1

		// Test 2: [2, 3, 1] - single cycle, no self-loop
		// Forms cycle: 1->2->3->1
		// Need 1 change to create a self-loop
		System.out.println("Test 2: " + solution.minChanges(new int[] {2, 3, 1})); // Expected: 1

		// Test 3: [2, 1, 4, 4] - two components
		// Component 1: 1->2->1 (cycle)
		// Component 2: 3->4->4 (self-loop)
		// Need 1 change to connect components
		System.out.println("Test 3: " + solution.minChanges(new int[] {2, 1, 4, 4})); // Expected: 1

		// Test 4: [1, 3, 2] - two components
		// Component 1: 1->1 (self-loop)
		// Component 2: 2->3->2 (cycle)
		// Need 1 change to connect components
		System.out.println("Test 4: " + solution.minChanges(new int[] {1, 3, 2})); // Expected: 1
	}
}
