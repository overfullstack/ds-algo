package leetcode.graph

import ds.graph.DiGraph
import utils.toPair

/* 09 Aug 2025 00:00 */

/** [2050. Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii) ⏰ TLE */
// !! Refer the Java version
fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
  val diGraph = DiGraph(relations.map { it.toPair() })
  val cache = mutableMapOf<Int, Int>()
  return diGraph.keys.maxOfOrNull { time[it - 1] + dfs(it, diGraph, time, cache) } ?: time.max()
}

fun dfs(node: Int, diGraph: DiGraph<Int>, time: IntArray, cache: MutableMap<Int, Int>): Int {
  cache[node]?.let {
    return it
  }
  return diGraph[node]
    ?.maxOf { time[it - 1] + dfs(it, diGraph, time, cache) }
    ?.also { cache[node] = it } ?: 0
}

fun main() {
  println(
    minimumTime(
      5,
      arrayOf(
        intArrayOf(1, 5),
        intArrayOf(2, 5),
        intArrayOf(3, 5),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
      ),
      intArrayOf(1, 2, 3, 4, 5),
    )
  )
}
