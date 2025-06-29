package leetcode.graph

/**
 * https://leetcode.com/problems/course-schedule/ prerequisites - (b <- a), to take course `b`, you
 * should finish course `a` !Unsubmitted - Time limit exceeding
 */
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
  val diGraph = prerequisites.toDiGraph()
  val visited = mutableSetOf<Int>()
  return try {
    diGraph.keys
      .asSequence()
      .filter { it !in visited }
      .all { it.dft(diGraph, visited.apply { add(it) }, setOf(it)) }
  } catch (e: IllegalArgumentException) {
    false
  }
}

private fun Int.dft(
  diGraph: Map<Int, Set<Int>>,
  visited: MutableSet<Int>,
  visitedInBrach: Set<Int>,
): Boolean =
  diGraph[this]
    ?.asSequence()
    ?.all { // ! You cannot filter visited here, as it skips `in vistedInBranch`
      when (it) {
        !in visited -> it.dft(diGraph, visited.apply { add(it) }, visitedInBrach + it)
        in visitedInBrach -> throw IllegalArgumentException("Graph has Cycle")
        else -> true // already visited
      }
    } ?: true

private fun Array<IntArray>.toDiGraph(): Map<Int, Set<Int>> =
  groupBy({ it[0] }, { it[1] }).mapValues { it.value.toSet() }
