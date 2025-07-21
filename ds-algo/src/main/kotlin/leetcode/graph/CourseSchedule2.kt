package leetcode.graph

import ds.graph.DiGraph

/**
 * https://leetcode.com/problems/course-schedule-ii/ Return the order of courses to be taken. 
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
  val diGraph = prerequisites.toDiGraph()
  for (courseNum in 0 until numCourses) {
    diGraph.putIfAbsent(courseNum, emptySet())
  }
  return try {
    diGraph.topologicalSort().toIntArray()
  } catch (_: IllegalArgumentException) {
    intArrayOf()
  }
}

private fun Array<IntArray>.toDiGraph(): DiGraph<Int> =
  DiGraph(groupBy({ it[0] }, { it[1] }).mapValues { it.value.toSet() }.toMutableMap())
