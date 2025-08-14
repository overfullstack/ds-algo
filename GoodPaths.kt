fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
    val n = vals.size
    
    // Build adjacency list for the tree
    val graph = Array(n) { mutableListOf<Int>() }
    for ((u, v) in edges) {
        graph[u].add(v)
        graph[v].add(u)
    }
    
    // Group nodes by their values, sorted in ascending order
    // This allows us to process smaller values first and build up to larger ones
    val valueToNodes = vals.indices
        .groupBy { vals[it] }
        .toSortedMap()
    
    var goodPaths = 0
    val parent = IntArray(n) { it } // Union-Find data structure
    val size = IntArray(n) { 1 }    // Size of each component
    
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
    
    // Process nodes in order of increasing values
    for ((value, nodes) in valueToNodes) {
        // For each node with current value, connect it to its neighbors
        // that have values <= current value
        for (node in nodes) {
            for (neighbor in graph[node]) {
                if (vals[neighbor] <= value) {
                    union(node, neighbor)
                }
            }
        }
        
        // Count good paths for current value
        // A good path exists between any two nodes in the same component
        // that both have the current value
        val componentCounts = mutableMapOf<Int, Int>()
        for (node in nodes) {
            val component = find(node)
            componentCounts[component] = componentCounts.getOrDefault(component, 0) + 1
        }
        
        // Calculate paths: for each component, if it has k nodes with current value,
        // we can form k*(k+1)/2 paths (including single nodes)
        for (count in componentCounts.values) {
            goodPaths += count * (count + 1) / 2
        }
    }
    
    return goodPaths
}
