package leetcode.graph

import ds.graph.EdgeWeightedDiGraph
import java.util.*

/* 30 Jul 2025 18:42 */

/**
 * [1928. Minimum Cost to Reach Destination in Time](https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time)
 */
fun minCost(maxTime: Int, edges: Array<IntArray>, passingFees: IntArray): Int {
  // Create undirected graph by adding edges in both directions
  // ! These are Bi-directional roads
  val diGraph =
    EdgeWeightedDiGraph(
      edges.flatMap { (from, to, time) -> listOf(Triple(from, to, time), Triple(to, from, time)) }
    )
  val pq = PriorityQueue(Comparator.comparingInt<Triple<Int, Int, Int>> { it.third })
  val nodeToTimeFromSource = mutableMapOf<Int, Int>()
  pq.add(Triple(0, 0, passingFees.first()))

  while (pq.isNotEmpty()) {
    val (node, timeFromSource, fee) = pq.poll()
    // ! Early termination
    if (nodeToTimeFromSource[node]?.let { timeFromSource >= it } ?: false) continue
    if (node == passingFees.lastIndex) return fee
    nodeToTimeFromSource[node] = timeFromSource
    diGraph[node]?.forEach { (to, timeFromNodeToTo) ->
      val newTime = timeFromSource + timeFromNodeToTo
      if (newTime <= maxTime && (nodeToTimeFromSource[to]?.let { newTime < it } ?: true)) {
        val newFee = fee + passingFees[to]
        pq.add(Triple(to, newTime, newFee))
      }
    }
  }

  return -1
}
