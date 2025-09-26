package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MinimumBuses {
	public static int minimumBuses(int[][] routes, int src, int dest) {
		// Create adjacency list for graph.
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		for (int i = 0; i < routes.length; i++) {
			for (int station : routes[i]) {
				if (!adjList.containsKey(station)) {
					adjList.put(station, new ArrayList<>());
				}
				adjList.get(station).add(i);
			}
		}

		// Create a queue with initial source and bus count of 0.
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {src, 0});

		// Create a set to contain visited routes of bus.
		Set<Integer> visitedBuses = new HashSet<>();

		// Iterate till queue is empty.
		while (!queue.isEmpty()) {
			// Pop station and and number of buses taken and store in variables.
			int[] current = queue.peek();
			int station = current[0];
			int busesTaken = current[1];
			queue.poll();

			// If we have reached the destination station, return number of buses taken.
			if (station == dest) {
				return busesTaken;
			}

			// If station is in graph, then iterate over the stations in graph
			// and if it is not already visited, enqueue all the stations in that bus
			// route along with an incremented bus count and mark the bus visited.
			if (adjList.containsKey(station)) {
				for (int bus : adjList.get(station)) {
					if (!visitedBuses.contains(bus)) {
						for (int s : routes[bus]) {
							queue.add(new int[] {s, busesTaken + 1});
						}
						visitedBuses.add(bus);
					}
				}
			}
		}

		// If the route is not found, return -1.
		return -1;
	}

	// Driver code
	static void main() {
		int[][][] routes = {
			{{2, 5, 7}, {4, 6, 7}},
			{{1, 12}, {4, 5, 9}, {9, 19}, {10, 12, 13}},
			{{1, 12}, {10, 5, 9}, {4, 19}, {10, 12, 13}},
			{{1, 9, 7, 8}, {3, 6, 7}, {4, 9}, {8, 2, 3, 7}, {2, 4, 5}},
			{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
		};
		int[] src = {2, 9, 1, 1, 4};
		int[] dest = {6, 12, 9, 5, 6};

		for (int i = 0; i < routes.length; i++) {
			System.out.print((i + 1) + ".\tBus Routes: ");
			System.out.print(Arrays.deepToString(routes[i]));
			System.out.println();
			System.out.println("\tSource: " + src[i]);
			System.out.println("\tDestination: " + dest[i]);
			System.out.println("\n\tMinimum Buses Required: " + minimumBuses(routes[i], src[i], dest[i]));
			System.out.println(new String(new char[100]).replace('\0', '-'));
		}
	}
}
