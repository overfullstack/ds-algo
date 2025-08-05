package educative.graph

import ds.graph.Graph
import ds.graph.TarjanNode
import utils.toPair

/* 05 Aug 2025 11:46 */

/**
 * [1192. Critical Connections in a
 * Network](https://leetcode.com/problems/critical-connections-in-a-network/) ⏱️Time limit exceeded
 */
fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
  val graph = Graph(connections.map { it.map { TarjanNode(it) }.toPair() })

  val visited = mutableSetOf<TarjanNode>()
  val bridges = mutableListOf<Pair<Int, Int>>()
  graph.keys
    .asSequence()
    .filter { it !in visited }
    .forEach { dfsPerGroup(it, graph, visited, bridges) }
  return bridges.map { it.toList() }
}

fun dfsPerGroup(
  node: TarjanNode,
  graph: Graph<TarjanNode>,
  visited: MutableSet<TarjanNode>,
  bridges: MutableList<Pair<Int, Int>>,
  parent: TarjanNode? = null,
): Int {
  visited += node
  var nextDisc = node.disc + 1
  val neighbors =
    graph[node]
      ?.asSequence()
      ?.filter { it != parent } // ! Critical
      ?.onEach { neighbor ->
        if (neighbor !in visited) {
          neighbor.disc = nextDisc
          neighbor.low = nextDisc
          nextDisc = dfsPerGroup(neighbor, graph, visited, bridges, node)
        }
      } ?: return nextDisc
  neighbors.minOfOrNull { it.low }?.let { node.low = it }
  bridges.addAll(neighbors.filter { it.low > node.disc }.map { node.value to it.value })
  return nextDisc
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
  val n = 4
  val connections = listOf(listOf(0, 1), listOf(1, 2), listOf(2, 0), listOf(1, 3))
  println(criticalConnections(n, connections))
  println(criticalConnectionsOptimized(n, connections))
}
