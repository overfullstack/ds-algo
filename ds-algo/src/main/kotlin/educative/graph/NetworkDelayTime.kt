package educative.graph

import ds.graph.EdgeWeightedDiGraph
import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.PriorityQueue

/* 15 Jul 2025 15:41 */

/** [743. Network Delay Time](https://leetcode.com/problems/network-delay-time) */
fun networkDelayTime(times: Array<IntArray>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph<Int>(times)
  // pq
  val pq = PriorityQueue(compareBy<WeightedEdge<Int>> { it.weight })
  pq.add(WeightedEdge(origin, 0))
  val visited = mutableSetOf<Int>()
  var timeToReachLastNode = 0
  while (pq.isNotEmpty()) {
    val (node, timeFromSource) = pq.poll()
    if (node !in visited) {
      visited += node
      timeToReachLastNode = timeFromSource
      graph[node]
        ?.filter { (to, _) -> to !in visited }
        ?.forEach { (to, timeFromToTo) -> pq.add(WeightedEdge(to, timeFromToTo + timeFromSource)) }
    }
  }
  return if (visited.size == n) timeToReachLastNode else -1
}

// * This also works, based on classic Dijkstra's algorithm
fun networkDelayTime2(times: Array<IntArray>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph<Int>(times)
  val pq = PriorityQueue(compareBy<WeightedEdge<Int>> { it.weight })
  val time = IntArray(n + 1) { Int.MAX_VALUE }

  time[origin] = 0
  pq.add(WeightedEdge(origin, 0))

  while (pq.isNotEmpty()) {
    val (node, timeFromSource) = pq.poll()
    if (timeFromSource <= time[node]) {
      graph[node]?.forEach { (to, timeFromToTo) ->
        val newDist = timeFromSource + timeFromToTo
        if (newDist < time[to]) {
          time[to] = newDist
          pq.add(WeightedEdge(to, newDist))
        }
      }
    }
  }

  val maxTime = time.slice(1..n).maxOrNull() ?: return -1
  return if (maxTime == Int.MAX_VALUE) -1 else maxTime
}

fun main() {
  println(
    networkDelayTime(arrayOf(intArrayOf(1, 2, 1), intArrayOf(2, 3, 2), intArrayOf(1, 3, 4)), 3, 1)
  ) // 3
}
