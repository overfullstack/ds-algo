package ga.overfullstack.ds

import java.util.ArrayDeque

@JvmInline value class DiGraphNode(val value: Int)

@JvmInline
value class DiGraph(
  private val adjacencyMap: MutableMap<DiGraphNode, Set<DiGraphNode>> = mutableMapOf()
) {

  fun addEdge(source: DiGraphNode, destination: DiGraphNode) {
    adjacencyMap.merge(source, setOf(destination)) { oldSet, _ -> oldSet + destination }
  }

  fun getNeighbours(node: DiGraphNode): Set<DiGraphNode>? = adjacencyMap[node]

  tailrec fun bfs(
    valToSearch: DiGraphNode,
    visited: Set<DiGraphNode> = setOf(),
    queue: ArrayDeque<DiGraphNode> = ArrayDeque(listOf(DiGraphNode(0)))
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
    currentNode: DiGraphNode,
    valToSearch: DiGraphNode,
    visited: Set<DiGraphNode> = setOf()
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
  fun dft(): List<DiGraphNode> {
    val visited = mutableSetOf<DiGraphNode>() // * Global Visited, as no need to backtrack.
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { sequenceOf(it) + it.dftPerBranch(visited.apply { add(it) }) }
      .toList()
  }

  private fun DiGraphNode.dftPerBranch(
    visited: MutableSet<DiGraphNode>,
    path: Sequence<DiGraphNode> = emptySequence()
  ): Sequence<DiGraphNode> =
    adjacencyMap[this]
      ?.asSequence()
      ?.filter { it !in visited }
      ?.flatMap { it.dftPerBranch(visited.apply { add(it) }, path + it) }
      ?: path

  /** DFT <- */

  /** -> DETECT CYCLE */
  fun hasCycle(): Boolean {
    val visited = mutableSetOf<DiGraphNode>()
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .any { it.hasCycle(visited.apply { add(it) }, setOf(it)) }
  }

  private fun DiGraphNode.hasCycle(
    visited: MutableSet<DiGraphNode>,
    visitedInBranch: Set<DiGraphNode>
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
  fun topologicalSort(): List<DiGraphNode> {
    val visited = mutableSetOf<DiGraphNode>() // * Global visited
    return adjacencyMap.keys
      .asSequence()
      .filter { it !in visited }
      .flatMap { it.topologicalSortPerBranch(visited.apply { add(it) }, setOf(it)) + it }
      .toList()
  }

  private fun DiGraphNode.topologicalSortPerBranch(
    visited: MutableSet<DiGraphNode>,
    visitedInBranch: Set<DiGraphNode>
  ): Sequence<DiGraphNode> =
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
}
