package leetcode.graph

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * Return the order of courses to be taken.
 * ! unsubmitted - TLE
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val diGraph = prerequisites.toDiGraph()
    val visited = mutableSetOf<Int>()
    return try {
        // Using range instead of keys, to accomodate nodes that don't have any dependency.
        (0 until numCourses).asSequence().filter { it !in visited }
            .flatMap { it.topologicalSort(diGraph, visited.apply { add(it) }, setOf(it)) + it }
            .toList().toIntArray()
    } catch (e: IllegalArgumentException) {
        intArrayOf()
    }
}

private fun Int.topologicalSort(
    diGraph: Map<Int, Set<Int>>,
    visited: MutableSet<Int>,
    visitedInBranch: Set<Int>
): Sequence<Int> =
    diGraph[this]?.asSequence()?.flatMap {
        when {
            // `visited.apply { add(it) }` coz we need to retain it across recursions. `visitedInBranch + it` no need to retain.
            it !in visited -> it.topologicalSort(
                diGraph,
                visited.apply { add(it) },
                visitedInBranch + it
            ) + it
            it in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
            else -> emptySequence() // This node is visited so can't contribute to any sequence.
        }
    } ?: emptySequence() // No connections.

private fun Array<IntArray>.toDiGraph(): Map<Int, Set<Int>> =
    groupBy({ it[0] }, { it[1] }).mapValues { it.value.toSet() }
