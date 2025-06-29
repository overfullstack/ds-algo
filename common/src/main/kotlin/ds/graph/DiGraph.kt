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

  fun getNeighbours(node: T): Set<T>? = adjacencyMap[node]

  fun bfs(valToSearch: T): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { bfs(valToSearch, visited, ArrayDeque(listOf(it))) }
  }

  private tailrec fun bfs(
    valToSearch: T,
    visited: MutableSet<T>,
    queue: ArrayDeque<T>,
  ): Boolean {
    return when {
      queue.isEmpty() -> false
      else -> {
        val node = queue.removeLast()
        when (node) {
          valToSearch -> true
          else -> {
            val neighbours = adjacencyMap[node]?.filter { it !in visited } ?: emptySet()
            queue.addAll(neighbours)
            visited.add(node)
            bfs(valToSearch, visited, queue)
          }
        }
      }
    }
  }

  fun dfs(valToSearch: T): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { dfs(it, valToSearch, visited.apply { add(it) }) }
  }

  private fun dfs(currentNode: T, valToSearch: T, visited: MutableSet<T>): Boolean =
    when (currentNode) {
      valToSearch -> true
      else -> {
        adjacencyMap[currentNode]
          ?.asSequence()
          ?.filter { it !in visited }
          ?.any { dfs(it, valToSearch, visited.apply { add(it) }) } == true
      }
    }

  /** -> DFT */
  fun dft(): List<T> {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { listOf(it) + it.dftPerBranch(visited.apply { add(it) }) }
      .toList()
  }

  private fun T.dftPerBranch(
    visited: MutableSet<T>,
  ): Sequence<T> =
    adjacencyMap[this]
      ?.asSequence()
      ?.filter { it !in visited }
      ?.flatMap { listOf(it) + it.dftPerBranch(visited.apply { add(it) }) }
      ?.distinct() ?: emptySequence()

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<T>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCycle(visited.apply { add(it) }) }
  }

  private fun T.hasCycle(visited: MutableSet<T>, visitedInBranch: Set<T> = setOf(this)): Boolean =
    adjacencyMap[this]?.any {
      when (it) {
        in visitedInBranch -> true
        in visited -> false // * A node can have two inward connections
        else -> it.hasCycle(visited.apply { add(it) }, visitedInBranch + it)
      }
    } == true

  /** DETECT CYCLE -> */

  /** -> TOPOLOGICAL SORT with Cycle Detection */
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
    visitedInBranch: Set<T> = setOf(this)
  ): Sequence<T> =
    adjacencyMap[this]?.asSequence()?.flatMap {
      when (it) { // * `visited.apply { add(it) }` coz we need to retain it across recursions.
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> emptySequence() // This node is visited so can't contribute to any sequence.
        else -> it.topologicalSortPerBranch(visited.apply { add(it) }, visitedInBranch + it) + it
      }
    } ?: emptySequence() // No connections.

  /** TOPOLOGICAL SORT with Cycle Detection -> */
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
