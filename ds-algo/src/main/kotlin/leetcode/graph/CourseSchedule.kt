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
      .all { it.dftPerGroup(diGraph, visited) }
  } catch (_: IllegalArgumentException) {
    false
  }
}

private fun Int.dftPerGroup(
  diGraph: Map<Int, Set<Int>>,
  visited: MutableSet<Int>,
  visitedInBranch: Set<Int> = setOf(this),
): Boolean {
  visited += this
  return diGraph[this]?.all {
    when (it) {
      in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
      in visited -> true // already visited
      else -> it.dftPerGroup(diGraph, visited, visitedInBranch + it) 
    }
  } ?: true
}

private fun Array<IntArray>.toDiGraph(): Map<Int, Set<Int>> =
  groupBy({ it[0] }, { it[1] }).mapValues { it.value.toSet() }
