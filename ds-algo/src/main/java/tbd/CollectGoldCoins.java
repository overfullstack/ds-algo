package tbd;

import java.util.*;

public class CollectGoldCoins {

	// State class to represent Chris's position and collected coins
	// Using a class makes the code more readable than array indices
	static class State {
		int row;
		int col;
		Set<String> collectedCoins;
		int distance;

		State(int row, int col, Set<String> collectedCoins, int distance) {
			this.row = row;
			this.col = col;
			// Create a new set to avoid mutation issues
			this.collectedCoins = new HashSet<>(collectedCoins);
			this.distance = distance;
		}

		// Generate a unique key for this state to track visited states
		// Combines position with sorted coin positions for consistent hashing
		String getStateKey() {
			List<String> sortedCoins = new ArrayList<>(collectedCoins);
			Collections.sort(sortedCoins);
			return row + "," + col + ":" + String.join(",", sortedCoins);
		}
	}

	public int minMoves(List<List<Integer>> maze, int x, int y) {
		int n = maze.size();
		int m = maze.getFirst().size();
		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = maze.get(i).get(j);
			}
		}

		// Identify all gold coin positions
		// Store as strings for easy set operations
		Set<String> allCoins = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					allCoins.add(i + "," + j);
				}
			}
		}

		// Edge case: If Alex's position is blocked, it's impossible to reach
		if (grid[x][y] == 1) {
			return -1;
		}

		// BFS setup with State objects for clarity
		Queue<State> queue = new LinkedList<>();
		// Track visited states using string representation
		// This prevents revisiting the same position with the same coins collected
		Set<String> visited = new HashSet<>();

		// Start from Chris's position (0, 0) with no coins collected
		State startState = new State(0, 0, new HashSet<>(), 0);
		queue.offer(startState);
		visited.add(startState.getStateKey());

		// Directions for movement: up, down, left, right
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		while (!queue.isEmpty()) {
			State current = queue.poll();

			// Check if we reached Alex's position with all coins collected
			// Success condition: at Alex's location AND collected all coins
			if (current.row == x && current.col == y && current.collectedCoins.equals(allCoins)) {
				return current.distance;
			}

			// Explore all four directions
			for (int[] dir : directions) {
				int newRow = current.row + dir[0];
				int newCol = current.col + dir[1];

				// Check if the new position is within bounds
				if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m) {
					continue;
				}

				// Skip if the cell is blocked
				if (grid[newRow][newCol] == 1) {
					continue;
				}

				// Create new state with updated position and coins
				State newState = new State(newRow, newCol, current.collectedCoins, current.distance + 1);

				// If there's a coin at the new position, add it to collected coins
				String posKey = newRow + "," + newCol;
				if (allCoins.contains(posKey)) {
					newState.collectedCoins.add(posKey);
				}

				// Generate unique key for this state
				String stateKey = newState.getStateKey();

				// Only visit if this state hasn't been visited before
				// This ensures we find the shortest path
				if (!visited.contains(stateKey)) {
					visited.add(stateKey);
					queue.offer(newState);
				}
			}
		}
		return -1;
	}

	static class State2 {
		int row;
		int col;
		int coinMask; // Bitmask representing collected coins
		int distance;

		State2(int row, int col, int coinMask, int distance) {
			this.row = row;
			this.col = col;
			this.coinMask = coinMask;
			this.distance = distance;
		}

		// Efficient state key using bit operations
		long getStateKey() {
			return ((long) row << 32) | ((long) col << 16) | coinMask;
		}
	}

	public int minMoves2(List<List<Integer>> maze, int x, int y) {
		int n = maze.size();
		int m = maze.getFirst().size();
		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = maze.get(i).get(j);
			}
		}

		// Map coin positions to bit indices for efficient bitmask operations
		List<int[]> coinPositions = new ArrayList<>();
		Map<String, Integer> coinIndexMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					coinPositions.add(new int[] {i, j});
					coinIndexMap.put(i + "," + j, coinPositions.size() - 1);
				}
			}
		}

		int totalCoins = coinPositions.size();
		if (totalCoins > 20) { // Bitmask limitation for reasonable performance
			throw new IllegalArgumentException("Too many coins for efficient processing");
		}

		// Edge case: If Alex's position is blocked, it's impossible to reach
		if (grid[x][y] == 1) {
			return -1;
		}

		// BFS setup with optimized state tracking
		Queue<State2> queue = new LinkedList<>();
		Set<Long> visited = new HashSet<>(); // Use Long for efficient state keys

		// Start from Chris's position (0, 0) with no coins collected
		State2 startState = new State2(0, 0, 0, 0);
		queue.offer(startState);
		visited.add(startState.getStateKey());

		// Target: all coins collected (all bits set)
		int allCoinsMask = (1 << totalCoins) - 1;

		// Directions for movement: up, down, left, right
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		while (!queue.isEmpty()) {
			State2 current = queue.poll();

			// Check if we reached Alex's position with all coins collected
			if (current.row == x && current.col == y && current.coinMask == allCoinsMask) {
				return current.distance;
			}

			// Explore all four directions
			for (int[] dir : directions) {
				int newRow = current.row + dir[0];
				int newCol = current.col + dir[1];

				// Check bounds and obstacles
				if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || grid[newRow][newCol] == 1) {
					continue;
				}

				// Calculate new coin mask using efficient lookup
				int newCoinMask = current.coinMask;
				String posKey = newRow + "," + newCol;
				Integer coinIndex = coinIndexMap.get(posKey);
				if (coinIndex != null) {
					newCoinMask |= (1 << coinIndex); // Set bit for this coin
				}

				// Create new state
				State2 newState = new State2(newRow, newCol, newCoinMask, current.distance + 1);
				long stateKey = newState.getStateKey();

				// Only visit if this state hasn't been visited before
				if (!visited.contains(stateKey)) {
					visited.add(stateKey);
					queue.offer(newState);
				}
			}
		}

		return -1; // No valid path found
	}
}
