fun canRouteMessage(
  coordinates: List<Pair<Int, Int>>,
  source: Int,
  destination: Int,
  wirelessRange: Int,
): Boolean {
  // Edge case: if source equals destination, message is already there
  if (source == destination) return true

  // Build adjacency list where routers connect if within wireless range
  // Using functional approach with sequences for efficient lazy evaluation
  val graph: Map<Int, Set<Int>> = buildRouterGraph(coordinates, wirelessRange)

  // Use DFS to find if a path exists from source to destination
  // Passing immutable visited set through recursion for functional purity
  return findPath(source, destination, graph, emptySet())
}

// Build the router connectivity graph based on wireless range
// Two routers connect if their Euclidean distance <= wirelessRange
private fun buildRouterGraph(
  coordinates: List<Pair<Int, Int>>,
  wirelessRange: Int,
): Map<Int, Set<Int>> =
  coordinates.indices.asSequence().associateWith { routerIndex ->
    // For each router, find all other routers within range
    coordinates.indices
      .asSequence()
      .filter { otherRouter ->
        // Skip self-connections and check if within range
        routerIndex != otherRouter &&
          isWithinWirelessRange(coordinates[routerIndex], coordinates[otherRouter], wirelessRange)
      }
      .toSet()
  }

// Recursive DFS with immutable state - functional programming style
// Returns true if destination is reachable from current router
private fun findPath(
  current: Int,
  destination: Int,
  graph: Map<Int, Set<Int>>,
  visited: Set<Int>,
): Boolean =
  when (current) {
    destination -> true

    // Pruning: already visited this router in current path
    // Prevents infinite cycles in the graph
    in visited -> false

    // Recursive case: try all unvisited neighbors
    // Using any() for short-circuit evaluation - stops at first successful path
    else -> graph[current]
      ?.asSequence()
      ?.filterNot { it in visited }
      ?.any { neighbor ->
        // Immutable state update: add current to visited for this branch
        findPath(neighbor, destination, graph, visited + current)
      } ?: false // No neighbors mean dead end
  }

// Check if two routers are within wireless communication range
// Uses squared distance comparison to avoid expensive sqrt operation
private fun isWithinWirelessRange(
  router1: Pair<Int, Int>,
  router2: Pair<Int, Int>,
  wirelessRange: Int,
): Boolean {
  val (x1, y1) = router1
  val (x2, y2) = router2

  // Calculate squared Euclidean distance
  // Comparing squared values avoids sqrt computation and floating point errors
  val deltaX = x2 - x1
  val deltaY = y2 - y1
  val squaredDistance = deltaX * deltaX + deltaY * deltaY

  // Compare against squared range for efficiency
  return squaredDistance <= wirelessRange * wirelessRange
}
