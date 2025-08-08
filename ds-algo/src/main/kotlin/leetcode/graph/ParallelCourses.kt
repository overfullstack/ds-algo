package leetcode.graph

import ds.graph.DiGraph

/** [1136 - Parallel Courses](https://leetcode.ca/2019-01-09-1136-Parallel-Courses/) */
fun minimumSemesters(relations: Array<Pair<Int, Int>>): Int {
  val diGraph = DiGraph(relations)
  val cache = mutableMapOf<Int, Int>()
  val visited = mutableSetOf<Int>()
  return try {
    diGraph.keys.maxOfOrNull { 1 + it.dftPerGroup(diGraph, cache, visited) } ?: 0
  } catch (_: IllegalArgumentException) {
    -1
  }
}

private fun Int.dftPerGroup(
  diGraph: DiGraph<Int>,
  cache: MutableMap<Int, Int>,
  visited: MutableSet<Int>,
  visitedInGroup: Set<Int> = setOf(this),
): Int {
  visited += this
  return diGraph[this]
    ?.maxOfOrNull {
      when (it) {
        in visitedInGroup -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> 0
        else -> 1 + it.dftPerGroup(diGraph, cache, visited, visitedInGroup + it)
      }
    }
    ?.also { cache[this] = it } ?: 0
}

fun main() {
  println(minimumSemesters(arrayOf(1 to 3, 2 to 3))) // 2
  println(minimumSemesters(arrayOf(1 to 0, 1 to 2))) // 2
  println(minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 1))) // -1
}
