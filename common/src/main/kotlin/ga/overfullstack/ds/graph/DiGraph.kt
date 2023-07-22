package ga.overfullstack.ds.graph

import com.squareup.moshi.JsonClass
import java.util.ArrayDeque

@JvmInline value class DiGraphNode<T>(val value: T)

@JvmInline
value class DiGraph(
  private val adjacencyMap: MutableMap<DiGraphNode<Int>, Set<DiGraphNode<Int>>> = mutableMapOf()
) {

  fun addEdge(source: DiGraphNode<Int>, destination: DiGraphNode<Int>) {
    adjacencyMap.merge(source, setOf(destination)) { oldSet, _ -> oldSet + destination }
  }

  fun getNeighbours(node: DiGraphNode<Int>): Set<DiGraphNode<Int>>? = adjacencyMap[node]

  tailrec fun bfs(
    valToSearch: DiGraphNode<Int>,
    visited: Set<DiGraphNode<Int>> = setOf(),
    queue: ArrayDeque<DiGraphNode<Int>> = ArrayDeque(listOf(DiGraphNode<Int>(0)))
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

  fun dfs(
    currentNode: DiGraphNode<Int>,
    valToSearch: DiGraphNode<Int>,
    visited: Set<DiGraphNode<Int>> = setOf()
  ): Boolean =
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
  fun dft(): List<DiGraphNode<Int>> {
    val visited = mutableSetOf<DiGraphNode<Int>>() // * Global Visited, as no need to backtrack.
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { sequenceOf(it) + it.dftPerBranch(visited.apply { add(it) }) }
      .toList()
  }

  private fun DiGraphNode<Int>.dftPerBranch(
    visited: MutableSet<DiGraphNode<Int>>,
    path: Sequence<DiGraphNode<Int>> = emptySequence()
  ): Sequence<DiGraphNode<Int>> =
    adjacencyMap[this]
      ?.asSequence()
      ?.filter { it !in visited }
      ?.flatMap { it.dftPerBranch(visited.apply { add(it) }, path + it) }
      ?: path

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<DiGraphNode<Int>>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCycle(visited.apply { add(it) }, setOf(it)) }
  }

  private fun DiGraphNode<Int>.hasCycle(
    visited: MutableSet<DiGraphNode<Int>>,
    visitedInBranch: Set<DiGraphNode<Int>>
  ): Boolean =
    adjacencyMap[this]?.any { // If visited but not a part of this branch, no cycle
      (it in visitedInBranch) ||
        (it !in visited && it.hasCycle(visited.apply { add(it) }, visitedInBranch + it))
      // First is to detect cycle, The second is to avoid cycle while traversing
      // These conditions can be flipped without any difference, as if it's not in `visited`, it
      // cannot be in `visitedInBranch`
    }
      ?: false

  /** DETECT CYCLE -> */

  /** -> TOPOLOGICAL SORT with Cycle Detection */
  fun topologicalSort(): List<DiGraphNode<Int>> {
    val visited = mutableSetOf<DiGraphNode<Int>>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerBranch(visited.apply { add(it) }, setOf(it)) + it }
      .toList()
  }

  private fun DiGraphNode<Int>.topologicalSortPerBranch(
    visited: MutableSet<DiGraphNode<Int>>,
    visitedInBranch: Set<DiGraphNode<Int>>
  ): Sequence<DiGraphNode<Int>> =
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
    data class JDiGraph(
      val graph: Graph
    ) {
      @JsonClass(generateAdapter = true)
      data class Graph(
        val nodes: List<Node>,
        val startNode: String
      ) {
        @JsonClass(generateAdapter = true)
        data class Node(
          val children: List<String>,
          val id: String,
          val value: String
        )
      }
    }
  }
}
