package ds

import java.util.*

typealias DiGraphNode = Int

data class DiGraph(val adjacencyMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()) {
    fun addEdge(source: Int, destination: Int) {
        adjacencyMap.merge(source, mutableSetOf(destination)) { oldSet, _ ->
            oldSet.add(destination)
            oldSet
        }
    }

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
                val neighbours = adjacencyMap.get(node)?.filter { !visited.contains(it) } ?: emptySet<Int>()
                queue.addAll(neighbours)
                bfs(valToSearch, visited, queue)
            }
        }

    fun dfs(currentNode: Int, valToSearch: Int, visited: MutableSet<Int> = mutableSetOf()): Boolean =
        if (currentNode == valToSearch) {
            true
        } else {
            visited.add(currentNode)
            val neighbours = adjacencyMap.get(currentNode)?.filter { !visited.contains(it) }
            neighbours?.any { dfs(it, valToSearch, visited) } ?: false
        }

    fun hasCycle(): Boolean {
        val visited = mutableSetOf<Int>()
        return adjacencyMap.keys.filter { !visited.contains(it) }.any { it.hasCycle(visited) }
    }

    private fun DiGraphNode.hasCycle(
        visited: MutableSet<Int>,
        visitedInBranch: MutableSet<Int> = mutableSetOf()
    ): Boolean {
        visited.add(this)
        visitedInBranch.add(this)

        val neighbors = adjacencyMap[this]
        neighbors?.forEach {
            if (!visited.contains(it) && it.hasCycle(visited, visitedInBranch)) {
                return true
            }
            if (visitedInBranch.contains(it)) {
                return true
            }
        }
        visitedInBranch.remove(this)
        return false
    }
}
