package leetcode.graph

import ds.graph.BiDiGraph
import utils.toPair

/**
 * [2242. Maximum Score of a Node
 * Sequence](https://leetcode.com/problems/maximum-score-of-a-node-sequence) `maximumScore` exceeds
 * time limit. `maximumScore2` doesn't
 */
fun maximumScore(scores: IntArray, edges: Array<IntArray>): Int {
  val graph = BiDiGraph(edges.map { it.toPair() })
  val graphWithTop3Neighbors =
    graph.mapValues { it.value.sortedByDescending { scores[it] }.take(3).toSet() }
  return edges
    .asSequence()
    .map { (u, v) -> findMaxScoreWithNeighboursForEdge(u, v, graphWithTop3Neighbors, scores) }
    .maxOrNull() ?: -1
}

fun findMaxScoreWithNeighboursForEdge(
  u: Int,
  v: Int,
  graphWithTop3Neighbors: Map<Int, Set<Int>>,
  scores: IntArray,
): Int {
  val uNeighboursWithoutV = graphWithTop3Neighbors[u]?.filter { it != v } ?: emptySet()
  val vNeighboursWithoutU = graphWithTop3Neighbors[v]?.filter { it != u } ?: emptySet()
  if (uNeighboursWithoutV.isEmpty() || vNeighboursWithoutU.isEmpty()) {
    return -1
  }
  return uNeighboursWithoutV
    .flatMap { uNeighbour ->
      vNeighboursWithoutU
        .asSequence()
        .filter { it != uNeighbour }
        .map { vNeighbour -> scores[uNeighbour] + scores[u] + scores[v] + scores[vNeighbour] }
    }
    .maxOrNull() ?: -1
}

fun maximumScore2(scores: IntArray, edges: Array<IntArray>): Int {
  val n: Int = scores.size
  val graph: Array<MutableList<Int>> = Array(n) { mutableListOf() }

  // Build adjacency list
  for (edge in edges) {
    val u: Int = edge[0]
    val v: Int = edge[1]
    graph[u].add(v)
    graph[v].add(u)
  }

  // For each node, keep only top 3 neighbors by score
  val top3Neighbors: Array<IntArray> = Array(n) { intArrayOf() }
  for (i in 0 until n) {
    if (graph[i].size > 3) {
      top3Neighbors[i] = graph[i].sortedByDescending { scores[it] }.take(3).toIntArray()
    } else {
      top3Neighbors[i] = graph[i].toIntArray()
    }
  }

  var maxScore: Int = -1

  // Try each edge as the middle edge (u -> v)
  for (edge in edges) {
    val u: Int = edge[0]
    val v: Int = edge[1]

    // Find best neighbors for u (excluding v) and v (excluding u)
    for (uNeighbor in top3Neighbors[u]) {
      if (uNeighbor == v) continue

      for (vNeighbor in top3Neighbors[v]) {
        if (vNeighbor == u || vNeighbor == uNeighbor) continue

        val currentScore: Int = scores[uNeighbor] + scores[u] + scores[v] + scores[vNeighbor]
        maxScore = maxOf(maxScore, currentScore)
      }
    }
  }

  return maxScore
}

fun main() {
  println(
    maximumScore(
      intArrayOf(5, 2, 9, 8, 4),
      arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(0, 2),
        intArrayOf(1, 3),
        intArrayOf(2, 4),
      ),
    )
  )
}
