fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
  val n = vals.size

  // Build adjacency list for the tree
  val graph = Array(n) { mutableListOf<Int>() }
  for ((u, v) in edges) {
    graph[u].add(v)
    graph[v].add(u)
  }

  // Group nodesIdxWithSameValue by their values, sorted in ascending order
  // This allows us to process smaller values first and build up to larger ones
  val valueToNodes = vals.indices.groupBy { vals[it] }.toSortedMap()

  var goodPaths = 0
  val parent = IntArray(n) { it } // Union-Find data structure
  val size = IntArray(n) { 1 } // Size of each component

  // Find function for Union-Find
  fun find(x: Int): Int {
    if (parent[x] != x) {
      parent[x] = find(parent[x]) // Path compression
    }
    return parent[x]
  }

  // Union function for Union-Find
  fun union(x: Int, y: Int) {
    val rootX = find(x)
    val rootY = find(y)
    if (rootX != rootY) {
      if (size[rootX] < size[rootY]) {
        parent[rootX] = rootY
        size[rootY] += size[rootX]
      } else {
        parent[rootY] = rootX
        size[rootX] += size[rootY]
      }
    }
  }

  // Process nodesIdxWithSameValue in order of increasing values
  for ((value, nodesIdxWithSameValue) in valueToNodes) {
    // For each node with current value, connect it to its neighbors
    // that have values <= current value
    for (nodeIdx in nodesIdxWithSameValue) {
      for (neighborIdx in graph[nodeIdx]) {
        if (vals[neighborIdx] <= value) {
          union(nodeIdx, neighborIdx)
        }
      }
    }

    // Count good paths for current value
    // A good path exists between any two nodesIdxWithSameValue in the same component
    // that both have the current value
    val componentCounts = mutableMapOf<Int, Int>()
    for (nodeIdx in nodesIdxWithSameValue) {
      val root = find(nodeIdx)
      componentCounts[root] = componentCounts.getOrDefault(root, 0) + 1
    }

    // Calculate paths: for each component, if it has k nodesIdxWithSameValue with current value,
    // we can form k*(k+1)/2 paths (including single nodesIdxWithSameValue)
    for (count in componentCounts.values) {
      goodPaths += count * (count + 1) / 2
    }
  }

  return goodPaths
}
