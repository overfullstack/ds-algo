package leetcode.graph

/** [3673 · Parallel Courses](https://www.lintcode.com/problem/3673/) */
fun minimumSemesters(relations: Array<IntArray>): Int {
  val diGraph = relations.groupBy({ it.first() }, { it.last() })
  val memo = mutableMapOf<Int, Int>()
  return try {
    diGraph.keys.maxOfOrNull { dftPerGroup(it, diGraph, memo) } ?: 0
  } catch (_: IllegalArgumentException) {
    -1
  }
}

private fun dftPerGroup(
  course: Int,
  diGraph: Map<Int, List<Int>>,
  memo: MutableMap<Int, Int>,
  visitedInGroup: Set<Int> = setOf(course),
): Int {
  memo[course]?.let { // ! Takes care of visited
    return it
  }
  val minSemesters =
    1 +
      (diGraph[course]?.maxOfOrNull {
        when (it) {
          in visitedInGroup -> throw IllegalArgumentException("Graph has Cycle")
          else -> dftPerGroup(it, diGraph, memo, visitedInGroup + it)
        }
      } ?: 0)
  memo[course] = minSemesters
  return minSemesters
}

fun main() {
  println(minimumSemesters(arrayOf(intArrayOf(1, 3), intArrayOf(2, 3)))) // 2
  println(
    minimumSemesters(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 1)))
  ) // -1
  println(
    minimumSemesters(
      arrayOf(
        intArrayOf(1, 2),
        intArrayOf(5, 2),
        intArrayOf(1, 5),
        intArrayOf(3, 2),
        intArrayOf(4, 1),
        intArrayOf(3, 5),
        intArrayOf(4, 2),
        intArrayOf(1, 3),
        intArrayOf(4, 3),
        intArrayOf(4, 5),
      )
    )
  ) // 5
}
