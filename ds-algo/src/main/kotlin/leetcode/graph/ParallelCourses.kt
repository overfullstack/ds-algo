package leetcode.graph

/** https://leetcode.ca/2019-01-09-1136-Parallel-Courses/ */
fun minimumSemesters(relations: Array<Pair<Int, Int>>): Int {
  val diGraph = relations.toDiGraph()
  val visited = mutableSetOf<Int>()
  return try {
    diGraph.keys
      .asSequence()
      .filter { it !in visited }
      .map { 1 + it.dftPerGroup(diGraph, visited, setOf(it)) }
      .maxOrNull() ?: 0
  } catch (e: IllegalArgumentException) {
    -1
  }
}

private fun Int.dftPerGroup(
  diGraph: Map<Int, Set<Int>>,
  visited: MutableSet<Int>,
  visitedInBranch: Set<Int>,
): Int =
  diGraph[this]
    ?.asSequence()
    ?.map {
      visited += this
      when (it) {
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        in visited -> 0
        else -> 1 + it.dftPerGroup(diGraph, visited, visitedInBranch + it)
      }
    }
    ?.maxOrNull() ?: 0

private fun Array<Pair<Int, Int>>.toDiGraph(): Map<Int, Set<Int>> =
  groupBy({ it.first }, { it.second }).mapValues { it.value.toSet() }

fun main() {
  println(minimumSemesters(arrayOf(1 to 3, 2 to 3))) // 2
  println(minimumSemesters(arrayOf(1 to 0, 1 to 2))) // 2
  println(minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 1))) // -1
}
