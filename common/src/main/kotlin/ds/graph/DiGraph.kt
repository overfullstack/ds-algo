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
      .any { bfsPerBranch(valToSearch, visited, ArrayDeque(listOf(it))) }
  }

  private tailrec fun bfsPerBranch(
    valToSearch: T,
    visited: MutableSet<T>,
    queue: ArrayDeque<T>,
  ): Boolean =
    when {
      queue.isEmpty() -> false
      else -> {
        val node = queue.poll()
        when (node) {
          valToSearch -> true
          in visited -> bfsPerBranch(valToSearch, visited, queue) // Skip this node, NoOp recursion
          else -> {
            visited.add(node) // Mark as visited before adding neighbors to avoid cycles
            val neighbours = adjacencyMap[node]?.filter { it !in visited } ?: emptyList()
            queue.addAll(neighbours)
            bfsPerBranch(valToSearch, visited, queue)
          }
        }
      }
    }

  fun dfs(valToSearch: T): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { dfsPerBranch(it, valToSearch, visited.apply { add(it) }) }
  }

  private fun dfsPerBranch(currentNode: T, valToSearch: T, visited: MutableSet<T>): Boolean =
    when (currentNode) {
      valToSearch -> true
      else -> {
        adjacencyMap[currentNode]
          ?.asSequence()
          ?.filter { it !in visited }
          ?.any { dfsPerBranch(it, valToSearch, visited.apply { add(it) }) } == true
      }
    }

  /** -> DFT */
  fun dft(): List<T> {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      // ! Using a `list(it)` for Order, otherwise Global visited captures all reachable nodes
      .flatMap { listOf(it) + it.dftPerBranch(visited) }
      .toList()
  }

  private fun T.dftPerBranch(visited: MutableSet<T>): Sequence<T> =
    when {
      this in visited -> emptySequence()
      else -> {
        visited += this
        adjacencyMap[this]
          ?.asSequence()
          ?.filter { it !in visited }
          ?.flatMap { listOf(it) + it.dftPerBranch(visited) }
          ?.distinct() ?: emptySequence()
      }
    }

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCyclePerBranch(visited.apply { add(it) }) }
  }

  private fun T.hasCyclePerBranch(
    visited: MutableSet<T>,
    visitedInBranch: Set<T> = setOf(this), // ! Notice global visited and visitedInBranch
  ): Boolean =
    adjacencyMap[this]?.any {
      when (it) {
        in visitedInBranch -> true
        in visited -> false // * A node can have two inward connections
        else -> it.hasCyclePerBranch(visited.apply { add(it) }, visitedInBranch + it)
      }
    } == true

  /** DETECT CYCLE -> */
  fun topologicalSort(): List<T> {
    val visited = mutableSetOf<T>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerBranch(visited.apply { add(it) }) + it }
      .toList()
  }

  private fun T.topologicalSortPerBranch(
    visited: MutableSet<T>,
    visitedInBranch: Set<T> = setOf(this),
  ): Sequence<T> =
    adjacencyMap[this]?.asSequence()?.flatMap {
      when (it) {
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> emptySequence() // This node was visited so can't contribute to any sequence.
        // Key depends on the list of values
        else -> it.topologicalSortPerBranch(visited.apply { add(it) }, visitedInBranch + it) + it
      }
    } ?: emptySequence() // No connections.

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
