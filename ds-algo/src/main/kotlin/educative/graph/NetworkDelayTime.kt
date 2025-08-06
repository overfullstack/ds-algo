package educative.graph

import ds.graph.EdgeWeightedDiGraph
import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.PriorityQueue

/* 15 Jul 2025 15:41 */

/**
 * [743. Network Delay Time](https://leetcode.com/problems/network-delay-time)
 * ⏱️Time limit exceeded
 */
fun networkDelayTime(times: List<Triple<Int, Int, Int>>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph(times)
  val pq = PriorityQueue(compareBy<WeightedEdge<Int>> { it.weight })
  pq.add(WeightedEdge(origin, 0))
  val visited = mutableSetOf<Int>()
  var maxTimeFromSource = Int.MIN_VALUE
  while (pq.isNotEmpty()) {
    val (node, timeFromSource) = pq.poll()
    if (node !in visited) {
      visited.add(node)
      // ! `minDelay` is the max time from source to `node`
      maxTimeFromSource = maxOf(maxTimeFromSource, timeFromSource)
      graph[node]
        ?.filter { (to, _) -> to !in visited }
        ?.forEach { (to, timeFromNodeToTo) ->
          pq.add(WeightedEdge(to, timeFromNodeToTo + timeFromSource))
        }
    }
  }
  return if (visited.size == n) maxTimeFromSource else -1
}

fun main() {
  val times = listOf(Triple(1, 2, 2))
  println(networkDelayTime(times, 2, 2))
}
