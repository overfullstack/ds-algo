package leetcode.graph

import ds.graph.DiGraph
import utils.toPair

/** https://leetcode.com/problems/course-schedule-ii/ Return the order of courses to be taken. */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
  val diGraph = DiGraph(prerequisites.map { it.toPair() })
  return try {
    diGraph.topologicalSort().toIntArray()
  } catch (_: IllegalArgumentException) { // For Cycle
    intArrayOf()
  }
}
