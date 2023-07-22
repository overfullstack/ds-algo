package leetcode.graph

import ga.overfullstack.ds.graph.DiGraphNode

fun minimumSemesters(relations: Array<Pair<Int, Int>>): Int {
  val diGraph = relations.toDiGraph()
  val visited = mutableSetOf<DiGraphNode<Int>>()
  return try {
    diGraph.keys
      .asSequence()
      .filter { it !in visited }
      .map { it.dft(diGraph, visited.apply { add(it) }, setOf(it)) }
      .maxOrNull()
      ?: 0
  } catch (e: IllegalArgumentException) {
    -1
  }
}

private fun DiGraphNode<Int>.dft(
  diGraph: Map<DiGraphNode<Int>, Set<DiGraphNode<Int>>>,
  visited: MutableSet<DiGraphNode<Int>>,
  visitedInBranch: Set<DiGraphNode<Int>>,
  maxNodesInPath: Int = 1
): Int =
  diGraph[this]
    ?.asSequence()
    ?.map {
      when (it) {
        !in visited ->
          it.dft(diGraph, visited.apply { add(it) }, visitedInBranch + it, maxNodesInPath + 1)
        in visitedInBranch -> throw IllegalArgumentException("Graph has Cycle")
        else -> maxNodesInPath
      }
    }
    ?.maxOrNull()
    ?: maxNodesInPath

private fun Array<Pair<Int, Int>>.toDiGraph(): Map<DiGraphNode<Int>, Set<DiGraphNode<Int>>> =
  groupBy({ DiGraphNode(it.first) }, { DiGraphNode(it.second) }).mapValues { it.value.toSet() }

fun main() {
  println(minimumSemesters(arrayOf(1 to 3, 2 to 3)))
  println(minimumSemesters(arrayOf(1 to 0, 1 to 2)))
  println(minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 1)))
}
