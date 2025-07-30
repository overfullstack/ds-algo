package leetcode.graph

fun minimumSemesters(relations: Array<Pair<Int, Int>>): Int {
  val diGraph = relations.toDiGraph()
  val visited = mutableSetOf<Int>()
  return try {
    diGraph.keys
      .asSequence()
      .filter { it !in visited }
      .map { it.dftPerBranch(diGraph, visited, setOf(it)) }
      .maxOrNull() ?: 0
  } catch (e: IllegalArgumentException) {
    -1
  }
}

private fun Int.dftPerBranch(
  diGraph: Map<Int, Set<Int>>,
  visited: MutableSet<Int>,
  visitedInBranch: Set<Int>,
  maxNodesInPath: Int = 1,
): Int {
  visited.add(this)
  return diGraph[this]
    ?.asSequence()
    ?.map {
      when (it) {
        !in visited -> it.dftPerBranch(diGraph, visited, visitedInBranch + it, maxNodesInPath + 1)
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        else -> maxNodesInPath
      }
    }
    ?.maxOrNull() ?: maxNodesInPath
}

private fun Array<Pair<Int, Int>>.toDiGraph(): Map<Int, Set<Int>> =
  groupBy({ it.first }, { it.second }).mapValues { it.value.toSet() }

fun main() {
  println(minimumSemesters(arrayOf(1 to 3, 2 to 3)))
  println(minimumSemesters(arrayOf(1 to 0, 1 to 2)))
  println(minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 1)))
}
