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
                val neighbours = adjacencyMap[node]?.filter { !visited.contains(it) } ?: emptySet()
                queue.addAll(neighbours)
                bfs(valToSearch, visited, queue)
            }
        }

    fun dfs(currentNode: Int, valToSearch: Int, visited: MutableSet<Int> = mutableSetOf()): Boolean =
        if (currentNode == valToSearch) {
            true
        } else {
            adjacencyMap[currentNode]?.asSequence()
                ?.filter { !visited.contains(it) }
                ?.any { dfs(it, valToSearch, visited.apply { add(it) }) }
                ?: false
        }

    fun dft(currentNode: DiGraphNode): List<Int> = dftSequence(currentNode).toList()

    private fun dftSequence(currentNode: DiGraphNode, visited: MutableSet<Int> = mutableSetOf(), path: Sequence<Int> = emptySequence()): Sequence<Int> =
        adjacencyMap[currentNode]?.asSequence()
            ?.filter { !visited.contains(it) }
            ?.flatMap { dftSequence(it, visited.apply { add(it) }, path + it) }
            ?: path

    fun hasCycle(): Boolean {
        val visited = mutableSetOf<Int>()
        return adjacencyMap.keys.asSequence().filter { !visited.contains(it) }
            .any { it.hasCycle(visited.apply { add(it) }, setOf(it)) }
    }

    private fun DiGraphNode.hasCycle(
        visited: MutableSet<Int>,
        visitedInBranch: Set<Int>
    ): Boolean =
        adjacencyMap[this]?.asSequence()?.any {
            (!visited.contains(it)
                    && it.hasCycle(visited.apply { add(it) }, visitedInBranch + it))
                    || visitedInBranch.contains(it)
        } ?: false

    fun topologicalSort(): List<Int> {
        val visited = mutableSetOf<Int>()
        return adjacencyMap.keys.asSequence().filter { !visited.contains(it) }
            .flatMap { it.topologicalSort(visited.apply { add(it) }, setOf(it)) + it }.toList()
    }

    private fun DiGraphNode.topologicalSort(visited: MutableSet<Int>, visitedInBranch: Set<Int>): Sequence<Int> =
        adjacencyMap[this]?.asSequence()?.flatMap {
            when {
                !visited.contains(it) -> it.topologicalSort(visited.apply { add(it) }, visitedInBranch + it) + it
                visitedInBranch.contains(it) -> throw IllegalArgumentException("Graph has Cycle")
                else -> emptySequence() // All connections are visited.
            }
        } ?: emptySequence() // No connections.
}
