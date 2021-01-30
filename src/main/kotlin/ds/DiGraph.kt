package ds

import java.util.*

typealias DiGraphNode = Int

data class DiGraph(private val adjacencyMap: MutableMap<Int, Set<Int>> = mutableMapOf()) {

    fun addEdge(source: Int, destination: Int) {
        adjacencyMap.merge(source, setOf(destination)) { oldSet, _ -> oldSet + destination }
    }

    fun getNeighbours(node: DiGraphNode): Set<DiGraphNode>? = adjacencyMap[node]

    tailrec fun bfs(
        valToSearch: Int,
        visited: Set<Int> = setOf(),
        queue: ArrayDeque<Int> = ArrayDeque(listOf(0))
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

    fun dfs(currentNode: Int, valToSearch: Int, visited: Set<Int> = setOf()): Boolean =
        if (currentNode == valToSearch) {
            true
        } else {
            adjacencyMap[currentNode]?.asSequence()
                ?.filter { it !in visited }
                ?.any { dfs(it, valToSearch, visited + it) }
                ?: false
        }

    /** -> DFT */

    fun dft(): List<Int> {
        val visited = mutableSetOf<Int>() // * Global Visited, as no need to backtrack.
        return adjacencyMap.keys.asSequence().filter { it !in visited }
            .flatMap { sequenceOf(it) + it.dftPerBranch(visited.apply { add(it) }) }.toList()
    }

    private fun DiGraphNode.dftPerBranch(
        visited: MutableSet<Int>,
        path: Sequence<Int> = emptySequence()
    ): Sequence<Int> =
        adjacencyMap[this]?.asSequence()
            ?.filter { it !in visited }
            ?.flatMap { it.dftPerBranch(visited.apply { add(it) }, path + it) }
            ?: path

    /** DFT <- */

    /** -> DETECT CYCLE */

    fun hasCycle(): Boolean {
        val visited = mutableSetOf<Int>()
        return adjacencyMap.keys.asSequence().filter { it !in visited }
            .any { it.hasCycle(visited.apply { add(it) }, setOf(it)) }
    }

    private fun DiGraphNode.hasCycle(
        visited: MutableSet<Int>,
        visitedInBranch: Set<Int>
    ): Boolean =
        adjacencyMap[this]?.any { // If visited but not a part of this branch, no cycle
            (it in visitedInBranch) || (it !in visited && it.hasCycle(visited.apply { add(it) }, visitedInBranch + it))
            // First is to detect cycle, The second is to avoid cycle while traversing
            // These conditions can be flipped without any difference, as if it's not in `visited`, it cannot be in `visitedInBranch`
        } ?: false

    /** DETECT CYCLE -> */

    /** -> TOPOLOGICAL SORT with Cycle Detection */

    fun topologicalSort(): List<Int> {
        val visited = mutableSetOf<Int>() // * Global visited
        return adjacencyMap.keys.asSequence().filter { it !in visited }
            .flatMap { it.topologicalSortPerBranch(visited.apply { add(it) }, setOf(it)) + it }.toList()
    }

    private fun DiGraphNode.topologicalSortPerBranch(visited: MutableSet<Int>, visitedInBranch: Set<Int>): Sequence<Int> =
        adjacencyMap[this]?.asSequence()?.flatMap {
            when {
                // * `visited.apply { add(it) }` coz we need to retain it across recursions. `visitedInBranch + it` no need to retain.
                it in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
                it in visited -> emptySequence() // This node is visited so can't contribute to any sequence.
                else -> it.topologicalSortPerBranch(visited.apply { add(it) }, visitedInBranch + it) + it
            }
        } ?: emptySequence() // No connections.

    /** TOPOLOGICAL SORT with Cycle Detection -> */
}
