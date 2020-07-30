package ds

import java.util.*

typealias DiGraphNode = Int

data class DiGraph(private val adjacencyMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()) {

    fun addEdge(source: Int, destination: Int) {
        adjacencyMap.merge(source, mutableSetOf(destination)) { oldSet, _ ->
            oldSet.apply { add(destination) }
        }
    }

    fun getNeighbours(node: DiGraphNode): Set<DiGraphNode>? = adjacencyMap[node]

    tailrec fun bfs(
        valToSearch: Int,
        visited: MutableSet<Int> = mutableSetOf(),
        queue: ArrayDeque<Int> = ArrayDeque(listOf(0))
    ): Boolean =
        if (queue.isEmpty()) {
            false
        } else {
            val node = queue.removeLast()
            if (node == valToSearch) {
                true
            } else {
                visited.add(node)
                val neighbours = adjacencyMap[node]?.filter { it !in visited } ?: emptySet()
                queue.addAll(neighbours)
                bfs(valToSearch, visited, queue)
            }
        }

    fun dfs(currentNode: Int, valToSearch: Int, visited: MutableSet<Int> = mutableSetOf()): Boolean =
        if (currentNode == valToSearch) {
            true
        } else {
            adjacencyMap[currentNode]?.asSequence()
                ?.filter { it !in visited }
                ?.any { dfs(it, valToSearch, visited.apply { add(it) }) }
                ?: false
        }

    /** -> DFT */

    fun dft(): List<Int> {
        val visited = mutableSetOf<Int>() // * Global Visited, as no need to backtrack.
        return adjacencyMap.keys.asSequence().filter { it !in visited }
            .flatMap { sequenceOf(it) + it.dft(visited.apply { add(it) }) }.toList()
    }

    private fun DiGraphNode.dft(
        visited: MutableSet<Int> = mutableSetOf(),
        path: Sequence<Int> = emptySequence()
    ): Sequence<Int> =
        adjacencyMap[this]?.asSequence()
            ?.filter { it !in visited }
            ?.flatMap { it.dft(visited.apply { add(it) }, path + it) }
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
        adjacencyMap[this]?.asSequence()?.any {
            (it !in visited &&
                    it.hasCycle(visited.apply { add(it) }, visitedInBranch + it)) ||
                    visitedInBranch.contains(it)
        } ?: false

    /** DETECT CYCLE -> */

    /** -> TOPOLOGICAL SORT with Cycle Detection */

    fun topologicalSort(): List<Int> {
        val visited = mutableSetOf<Int>()
        return adjacencyMap.keys.asSequence().filter { it !in visited }
            .flatMap { it.topologicalSort(visited.apply { add(it) }, setOf(it)) + it }.toList()
    }

    private fun DiGraphNode.topologicalSort(visited: MutableSet<Int>, visitedInBranch: Set<Int>): Sequence<Int> =
        adjacencyMap[this]?.asSequence()?.flatMap {
            when {
                // `visited.apply { add(it) }` coz we need to retain it across recursions. `visitedInBranch + it` no need to retain.
                it !in visited -> it.topologicalSort(visited.apply { add(it) }, visitedInBranch + it) + it
                it in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
                else -> emptySequence() // This node is visited so can't contribute to any sequence.
            }
        } ?: emptySequence() // No connections.

    /** TOPOLOGICAL SORT with Cycle Detection -> */
}
