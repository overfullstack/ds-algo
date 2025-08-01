package ds.graph

import com.salesforce.revoman.input.readFileToString
import java.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class DiGraph<T>(private val adjacencyMap: MutableMap<T, Set<T>> = mutableMapOf()) :
  MutableMap<T, Set<T>> by adjacencyMap {

  fun addEdge(source: T, destination: T) {
    adjacencyMap.merge(source, setOf(destination), Set<T>::plus)
  }

  fun getNeighbours(node: T): Set<T> = adjacencyMap[node] ?: emptySet()

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
          ?.any { dfsPerGroup(it, valToSearch, visited) } == true
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
    visitedInBranch: Set<T> = setOf(this), // ! Notice global visited and visitedInBranch
  ): Boolean {
    visited += this
    return adjacencyMap[this]?.any {
      when (it) {
        in visitedInBranch -> true // ! visitedInBranch check has to be before visited check
        in visited -> false // * A node can have two inward connections
        else -> it.hasCyclePerGroup(visited, visitedInBranch + it)
      }
    } == true
  }

  /** DETECT CYCLE -> */
  fun topologicalSort(): List<T> {
    val visited = mutableSetOf<T>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerBranch(visited) + it }
      .toList()
  }

  private fun T.topologicalSortPerBranch(
    visited: MutableSet<T>,
    visitedInBranch: Set<T> = setOf(this),
  ): Sequence<T> {
    visited += this
    return adjacencyMap[this]?.asSequence()?.flatMap {
      when (it) {
        // ! visitedInBranch check has to be before visited check
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> emptySequence() // This node was visited so can't contribute to any sequence.
        // Key depends on the list of values
        else -> it.topologicalSortPerBranch(visited, visitedInBranch + it) + it
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
