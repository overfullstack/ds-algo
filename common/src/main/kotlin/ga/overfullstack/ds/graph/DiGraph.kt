package ga.overfullstack.ds.graph

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import ga.overfullstack.utils.readFileToString
import java.util.ArrayDeque

class DiGraph<T>(
  private val adjacencyMap: MutableMap<T, Set<T>> = mutableMapOf(),
  private var startNode: T? = null
) : MutableMap<T, Set<T>> by adjacencyMap {

  fun addEdge(source: T, destination: T) {
    adjacencyMap.merge(source, setOf(destination)) { oldSet, _ -> oldSet + destination }
  }

  fun getNeighbours(node: T): Set<T>? = adjacencyMap[node]

  tailrec fun bfs(
    valToSearch: T,
    visited: Set<T> = setOf(),
    queue: ArrayDeque<T> = ArrayDeque(listOf(startNode))
  ): Boolean =
    if (queue.isEmpty()) {
      false
    } else {
      val node = queue.removeLast()
      if (node == valToSearch) {
        true
      } else {
        val neighbours = adjacencyMap[node]?.filter { it !in visited } ?: emptySet()
        queue.addAll(neighbours)
        bfs(valToSearch, visited + node, queue)
      }
    }

  fun dfs(currentNode: T, valToSearch: T, visited: Set<T> = setOf()): Boolean =
    if (currentNode == valToSearch) {
      true
    } else {
      adjacencyMap[currentNode]
        ?.asSequence()
        ?.filter { it !in visited }
        ?.any { dfs(it, valToSearch, visited + it) }
        ?: false
    }

  /** -> DFT */
  fun dft(): List<T> {
    val visited = mutableSetOf<T>() // * Global Visited, as no need to backtrack.
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.dftPerBranch(visited) }
      .toList()
  }

  private fun T.dftPerBranch(
    visited: MutableSet<T>,
    path: Sequence<T> = sequenceOf(this)
  ): Sequence<T> {
    visited.add(this)
    val neighbors = adjacencyMap[this]
    if (neighbors.isNullOrEmpty()) {
      return path
    }
    return neighbors
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.dftPerBranch(visited, path + it) }
      .distinct()
  }

  /** DFT Mutable approach */
  private fun T.dftForAllNeighbours(
    visited: MutableSet<T>,
    path: MutableList<T> = mutableListOf()
  ): List<T> {
    path.add(this)
    visited.add(this)

    val neighbors = adjacencyMap[this]
    if (neighbors.isNullOrEmpty()) {
      return path
    }
    for (neighbor in neighbors) {
      if (neighbor !in visited) {
        neighbor.dftForAllNeighbours(visited, path)
      }
    }
    return path
  }

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCycle(visited.apply { add(it) }, setOf(it)) }
  }

  private fun T.hasCycle(visited: MutableSet<T>, visitedInBranch: Set<T>): Boolean =
    adjacencyMap[this]?.any { // If visited but not a part of this branch, no cycle
      (it in visitedInBranch) ||
        (it !in visited && it.hasCycle(visited.apply { add(it) }, visitedInBranch + it))
      // The First is to detect cycle; The second is to avoid cycle while traversing
      // These conditions can be flipped without any difference, as if it's not in `visited`, it
      // cannot be in `visitedInBranch`
    }
      ?: false

  /** DETECT CYCLE -> */

  /** -> TOPOLOGICAL SORT with Cycle Detection */
  fun topologicalSort(): List<T> {
    val visited = mutableSetOf<T>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerBranch(visited.apply { add(it) }, setOf(it)) + it }
      .toList()
  }

  private fun T.topologicalSortPerBranch(
    visited: MutableSet<T>,
    visitedInBranch: Set<T>
  ): Sequence<T> =
    adjacencyMap[this]?.asSequence()?.flatMap {
      when (it) { // * `visited.apply { add(it) }` coz we need to retain it across recursions.
        // `visitedInBranch + it` no need to retain.
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> emptySequence() // This node is visited so can't contribute to any sequence.
        else -> it.topologicalSortPerBranch(visited.apply { add(it) }, visitedInBranch + it) + it
      }
    }
      ?: emptySequence() // No connections.

  /** TOPOLOGICAL SORT with Cycle Detection -> */
  companion object {
    @JsonClass(generateAdapter = true)
    data class JDiGraph(val graph: Graph) {
      @JsonClass(generateAdapter = true)
      data class Graph(val nodes: List<JGraphNode>, val startNode: String) {
        @JsonClass(generateAdapter = true)
        data class JGraphNode(val children: List<String>, val id: String, val value: String)
      }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToDiGraph(jsonFilePath: String): DiGraph<String> {
      val graphJson = readFileToString(jsonFilePath)
      val graphAdapter = Moshi.Builder().build().adapter<JDiGraph>()
      val jGraph = graphAdapter.fromJson(graphJson)!!
      return DiGraph(
        jGraph.graph.nodes.associate { it.value to it.children.toSet() }.toMutableMap(),
        jGraph.graph.startNode
      )
    }
  }
}
