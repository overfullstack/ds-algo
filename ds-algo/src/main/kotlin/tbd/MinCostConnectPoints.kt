import kotlin.math.abs

fun minCostConnectPoints(points: Array<IntArray>): Int {
  val n = points.size

  // Edge case: if only one point, no connections needed
  if (n <= 1) return 0

  // Generate all possible edges with their costs
  // Each edge connects two points with cost = Manhattan distance
  val edges = mutableListOf<Triple<Int, Int, Int>>()

  for (i in 0 until n) {
    for (j in i + 1 until n) {
      val cost = manhattanDistance(points[i], points[j])
      edges.add(Triple(i, j, cost))
    }
  }

  // Sort edges by cost in ascending order (Kruskal's algorithm)
  edges.sortBy { it.third }

  // Union-Find data structure to track connected components
  val parent = IntArray(n) { it }
  val rank = IntArray(n) { 0 }

  var totalCost = 0
  var edgesUsed = 0

  // Process edges in order of increasing cost
  for ((u, v, cost) in edges) {
    // If adding this edge doesn't create a cycle, include it
    if (union(u, v, parent, rank)) {
      totalCost += cost
      edgesUsed++

      // MST has exactly n-1 edges
      if (edgesUsed == n - 1) break
    }
  }

  return totalCost
}

// Calculate Manhattan distance between two points
// Manhattan distance = |x1 - x2| + |y1 - y2|
private fun manhattanDistance(point1: IntArray, point2: IntArray): Int {
  val (x1, y1) = point1
  val (x2, y2) = point2
  return abs(x1 - x2) + abs(y1 - y2)
}

// Find function for Union-Find with path compression
private fun find(x: Int, parent: IntArray): Int {
  if (parent[x] != x) {
    parent[x] = find(parent[x], parent) // Path compression
  }
  return parent[x]
}

// Union function for Union-Find with union by rank
private fun union(x: Int, y: Int, parent: IntArray, rank: IntArray): Boolean {
  val rootX = find(x, parent)
  val rootY = find(y, parent)

  // If already in same component, adding this edge would create a cycle
  if (rootX == rootY) return false

  // Union by rank for balanced trees
  if (rank[rootX] < rank[rootY]) {
    parent[rootX] = rootY
  } else if (rank[rootX] > rank[rootY]) {
    parent[rootY] = rootX
  } else {
    parent[rootY] = rootX
    rank[rootX]++
  }

  return true
}
