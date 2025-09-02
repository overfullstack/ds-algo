package educative.graph

import ds.graph.BiDiGraph
import ds.graph.TarjanNode
import utils.toPair

/* 05 Aug 2025 11:46 */

/**
 * [1192. Critical Connections in a
 * Network](https://leetcode.com/problems/critical-connections-in-a-network/) ⏰ TLE
 */
fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
  // ! isNodeTypePrimitive = false to reuse same TarjanNode instances
  // ! This may be causing TLE
  val graph = BiDiGraph(connections.map { it.map { TarjanNode(it) }.toPair() }, false)
  val visited = mutableSetOf<TarjanNode>()
  var nextDiscovery = 0
  val bridges =
    graph.keys
      .asSequence()
      .filter { it !in visited }
      .onEach {
        it.discovery = nextDiscovery
        it.low = nextDiscovery
      }
      .flatMap {
        val (nextDisc, bridges) = dfsPerGroup(it, graph, visited)
        nextDiscovery = nextDisc
        bridges
      }
      .toList()
  return bridges.map { it.toList() }
}

fun dfsPerGroup(
  node: TarjanNode,
  graph: BiDiGraph<TarjanNode>,
  visited: MutableSet<TarjanNode>,
  parent: TarjanNode? = null,
): Pair<Int, Sequence<Pair<Int, Int>>> {
  visited += node
  val allNeighborsExceptParent = graph[node]?.asSequence()?.filter { it != parent }
  val (nextDiscAfterChildren, bridgesFromChildren) =
    allNeighborsExceptParent?.fold(node.discovery + 1 to emptySequence<Pair<Int, Int>>()) {
      (time, bridges),
      neighbor ->
      when {
        neighbor in visited -> {
          node.low = minOf(node.low, neighbor.discovery) // ! neighbor.discovery
          time to bridges
        }
        else -> {
          neighbor.discovery = time
          neighbor.low = time
          val (nextDiscAfterChildren, bridgesFromChildren) =
            dfsPerGroup(neighbor, graph, visited, node)
          node.low = minOf(node.low, neighbor.low) // ! neighbor.low
          val newBridges =
            if (neighbor.low > node.discovery) bridges + (node.value to neighbor.value) else bridges
          nextDiscAfterChildren to (newBridges + bridgesFromChildren)
        }
      }
    } ?: (node.discovery + 1 to emptySequence()) // For leaf nodes

  return nextDiscAfterChildren to bridgesFromChildren
}

/**
 * Performance-optimized version using primitive arrays and direct adjacency list Time: O(V + E),
 * Space: O(V + E)
 */
fun criticalConnectionsOptimized(n: Int, connections: List<List<Int>>): List<List<Int>> {
  // Build adjacency list using primitive arrays for better performance
  val adj: Array<MutableList<Int>> = Array(n) { mutableListOf() }
  connections.forEach { (u, v) ->
    adj[u].add(v)
    adj[v].add(u)
  }

  val visited = BooleanArray(n)
  val disc = IntArray(n)
  val low = IntArray(n)
  val bridges: MutableList<List<Int>> = mutableListOf()
  var timer = 0
  // DFS for each unvisited component
  for (i in 0 until n) {
    if (!visited[i]) {
      timer = dfsOptimized(i, -1, adj, visited, disc, low, bridges, timer)
    }
  }
  return bridges
}

private fun dfsOptimized(
  node: Int,
  parent: Int,
  adj: Array<MutableList<Int>>,
  visited: BooleanArray,
  disc: IntArray,
  low: IntArray,
  bridges: MutableList<List<Int>>,
  timer: Int,
): Int {
  var currentTimer: Int = timer
  visited[node] = true
  disc[node] = currentTimer
  low[node] = currentTimer
  currentTimer++
  // Traverse all neighbors
  for (neighbor in adj[node]) {
    if (neighbor == parent) continue // Skip parent edge
    if (!visited[neighbor]) {
      // Tree edge - recurse
      currentTimer = dfsOptimized(neighbor, node, adj, visited, disc, low, bridges, currentTimer)
      // Update low value
      low[node] = minOf(low[node], low[neighbor])
      // Check if edge is a bridge
      if (low[neighbor] > disc[node]) {
        bridges.add(listOf(node, neighbor))
      }
    } else {
      // Back edge - update low value
      low[node] = minOf(low[node], disc[neighbor])
    }
  }
  return currentTimer
}

fun main() {
  /*val connections = listOf(listOf(0, 1), listOf(1, 2), listOf(2, 0), listOf(1, 3))
  println(criticalConnections(4, connections))
  println(criticalConnectionsOptimized(4, connections))*/

  val connections2 =
    listOf(
      listOf(0, 1),
      listOf(1, 2),
      listOf(2, 0),
      listOf(1, 3),
      listOf(3, 4),
      listOf(4, 5),
      listOf(5, 3),
    )
  println(criticalConnections(6, connections2)) // [[1, 3]]
  // println(criticalConnectionsOptimized(6, connections2))
}
