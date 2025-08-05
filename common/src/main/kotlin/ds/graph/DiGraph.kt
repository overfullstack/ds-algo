package ds.graph

import com.salesforce.revoman.input.readFileToString
import java.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class DiGraph<T>(
  private val adjacencyMap: MutableMap<T, Set<T>> = mutableMapOf(),
  val isPrimitiveType: Boolean = false,
) : MutableMap<T, Set<T>> by adjacencyMap {

  val allNodes: Set<T>
    get() = adjacencyMap.keys + adjacencyMap.values.flatten()

  constructor(edges: List<Pair<T, T>>) : this() {
    edges.forEach { (source, destination) -> addEdge(source, destination) }
  }

  fun addEdge(source: T, destination: T) {
    val actualDestination =
      if (isPrimitiveType) destination
      else allNodes.firstOrNull { it == destination } ?: destination
    adjacencyMap.merge(source, setOf(actualDestination), Set<T>::plus)
  }

  fun getNeighbours(node: T): Set<T> = adjacencyMap[node] ?: emptySet()

  /** Gets all nodes in the graph (both sources and destinations). */
  private fun getAllNodes(): Set<T> {
    val allNodes = mutableSetOf<T>()
    allNodes.addAll(adjacencyMap.keys) // Add all source nodes
    adjacencyMap.values.forEach { destinations ->
      allNodes.addAll(destinations) // Add all destination nodes
    }
    return allNodes
  }

  fun bfs(valToSearch: T): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { bfsPerGroup(valToSearch, visited, ArrayDeque(listOf(it))) }
  }

  private tailrec fun bfsPerGroup(
    valToSearch: T,
    visited: MutableSet<T>,
    queue: ArrayDeque<T>,
  ): Boolean =
    when {
      queue.isEmpty() -> false
      else -> {
        when (val node = queue.poll()) {
          valToSearch -> true
          // Skip this node, NoOp recursion with a polled queue
          in visited -> bfsPerGroup(valToSearch, visited, queue)
          else -> {
            visited += node // Mark as visited before adding neighbors to avoid cycles
            val neighbours = adjacencyMap[node]?.filter { it !in visited } ?: emptyList()
            queue.addAll(neighbours)
            bfsPerGroup(valToSearch, visited, queue)
          }
        }
      }
    }

  fun dfs(valToSearch: T): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { dfsPerGroup(it, valToSearch, visited) }
  }

  private fun dfsPerGroup(currentNode: T, valToSearch: T, visited: MutableSet<T>): Boolean =
    when (currentNode) {
      valToSearch -> true
      else -> {
        visited += currentNode
        adjacencyMap[currentNode]
          ?.asSequence()
          ?.filter { it !in visited }
          ?.any { dfsPerGroup(it, valToSearch, visited) } ?: false
      }
    }

  /** -> DFT */
  fun dft(): List<T> {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      // ! Using a `list(it)` for Order, otherwise Global visited captures all reachable nodes
      .flatMap { listOf(it) + it.dftPerGroup(visited) }
      .toList()
  }

  private fun T.dftPerGroup(visited: MutableSet<T>): Sequence<T> =
    when {
      this in visited -> emptySequence()
      else -> {
        visited += this
        adjacencyMap[this]
          ?.asSequence()
          ?.filter { it !in visited }
          ?.flatMap { listOf(it) + it.dftPerGroup(visited) }
          ?.distinct() ?: emptySequence()
      }
    }

  fun dftGroupsSize(): List<Int> {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .map { 1 + it.dftGroupSize(visited) }
      .toList()
  }

  private fun T.dftGroupSize(visited: MutableSet<T>): Int =
    when {
      this in visited -> 0
      else -> {
        visited += this
        adjacencyMap[this]?.filter { it !in visited }?.sumOf { 1 + it.dftGroupSize(visited) } ?: 0
      }
    }

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCyclePerGroup(visited) }
  }

  private fun T.hasCyclePerGroup(
    visited: MutableSet<T>,
    visitedInGroup: Set<T> = setOf(this), // ! Notice global visited and visitedInGroup
  ): Boolean {
    visited += this
    return adjacencyMap[this]?.any {
      when (it) {
        in visitedInGroup -> true // ! visitedInGroup check has to be before visited check
        // * A node can have two inward connections. If it's visited, it's already checked for cycle
        in visited -> false
        else -> it.hasCyclePerGroup(visited, visitedInGroup + it)
      }
    } ?: false
  }

  /** CONNECTIVITY CHECKS */

  /**
   * Checks if the graph is weakly connected. A directed graph is weakly connected if replacing all
   * directed edges with undirected edges produces a connected undirected graph.
   */
  fun isWeaklyConnected(): Boolean {
    val allNodes = getAllNodes()
    if (allNodes.isEmpty()) return true

    // Create undirected adjacency representation
    val undirectedAdjacency = mutableMapOf<T, MutableSet<T>>()

    // Add all nodes
    allNodes.forEach { node -> undirectedAdjacency[node] = mutableSetOf() }

    // Add edges in both directions
    adjacencyMap.forEach { (source, destinations) ->
      destinations.forEach { dest ->
        undirectedAdjacency[source]!!.add(dest)
        undirectedAdjacency[dest]!!.add(source)
      }
    }

    // Check if all nodes are reachable from any starting node using BFS
    val startNode = allNodes.first()
    val visited = mutableSetOf<T>()
    val queue = ArrayDeque<T>()

    queue.add(startNode)
    visited.add(startNode)

    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      undirectedAdjacency[current]?.forEach { neighbor ->
        if (neighbor !in visited) {
          visited.add(neighbor)
          queue.add(neighbor)
        }
      }
    }

    return visited.size == allNodes.size
  }

  /**
   * Checks if the graph is strongly connected. A directed graph is strongly connected if there is a
   * directed path from every node to every other node.
   */
  fun isStronglyConnected(): Boolean {
    val allNodes = getAllNodes()
    if (allNodes.isEmpty()) return true
    if (allNodes.size == 1) return true

    // Step 1: Check if all nodes are reachable from the first node
    val startNode = allNodes.first()
    val reachableFromStart = getReachableNodes(startNode)

    if (reachableFromStart.size != allNodes.size) {
      return false
    }

    // Step 2: Create transpose graph and check if all nodes are reachable from start node
    val transposeGraph = createTransposeGraph()
    val reachableInTranspose = transposeGraph.getReachableNodes(startNode)

    return reachableInTranspose.size == allNodes.size
  }

  /** Gets all nodes reachable from the given start node using DFS. */
  private fun getReachableNodes(startNode: T): Set<T> {
    val visited = mutableSetOf<T>()
    val stack = ArrayDeque<T>()

    stack.add(startNode)

    while (stack.isNotEmpty()) {
      val current = stack.removeLast()
      if (current !in visited) {
        visited.add(current)
        adjacencyMap[current]?.forEach { neighbor ->
          if (neighbor !in visited) {
            stack.add(neighbor)
          }
        }
      }
    }

    return visited
  }

  /** Creates a transpose graph (all edges reversed). */
  private fun createTransposeGraph(): DiGraph<T> {
    val transposeAdjacency = mutableMapOf<T, Set<T>>()
    val allNodes = getAllNodes()

    // Initialize all nodes with empty sets
    allNodes.forEach { node -> transposeAdjacency[node] = emptySet() }

    // Reverse all edges
    adjacencyMap.forEach { (source, destinations) ->
      destinations.forEach { dest ->
        transposeAdjacency[dest] = transposeAdjacency[dest]!! + source
      }
    }

    return DiGraph(transposeAdjacency)
  }

  /** DETECT CYCLE -> */
  fun topologicalSort(): List<T> {
    val visited = mutableSetOf<T>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerGroup(visited) + it }
      .toList()
  }

  private fun T.topologicalSortPerGroup(
    visited: MutableSet<T>,
    visitedInGroup: Set<T> = setOf(this),
  ): Sequence<T> {
    visited += this
    return adjacencyMap[this]?.asSequence()?.flatMap {
      when (it) {
        // ! visitedInGroup check has to be before visited check
        in visitedInGroup -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> emptySequence() // This node was visited so can't contribute to any sequence.
        // Key depends on the list of values
        else -> it.topologicalSortPerGroup(visited, visitedInGroup + it) + it
      }
    } ?: emptySequence() // No connections.
  }

  companion object {
    @Serializable
    data class JDiGraph(val graph: Graph) {
      @Serializable
      data class Graph(val nodes: List<JGraphNode>, val startNode: String) {
        @Serializable
        data class JGraphNode(val children: List<String>, val id: String, val value: String)
      }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToDiGraph(jsonFilePath: String): DiGraph<String> {
      val graphJson = readFileToString(jsonFilePath)
      val jGraph = Json.decodeFromString<JDiGraph>(graphJson)
      return DiGraph(
        jGraph.graph.nodes.associate { it.value to it.children.toSet() }.toMutableMap()
      )
    }
  }
}
