package educative.graph

import ds.graph.EdgeWeightedDiGraph
import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.PriorityQueue

/* 15 Jul 2025 15:41 */

fun networkDelayTime(times: List<Triple<Int, Int, Int>>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph(times)
  if (graph[origin].isNullOrEmpty()) return -1
  // ! This has to be sorted by weight, so we reach neighbours through shortest path
  val pq = PriorityQueue(Comparator.comparingInt<WeightedEdge<Int>> { it.weight })
  pq.addAll(graph[origin]!!)
  val visited = mutableSetOf(origin)
  var minDelay = Int.MIN_VALUE
  while (pq.isNotEmpty()) {
    val (_, to, time) = pq.poll()
    if (to !in visited) {
      visited.add(to)
      minDelay = maxOf(minDelay, time)
      graph[to]
        ?.filter { (_, to, _) -> to !in visited }
        ?.forEach { (from, to, time) -> pq.add(WeightedEdge(from, to, time + minDelay)) }
    }
  }
  return if (visited.size == n) minDelay else -1
}

fun main() {
  val times = listOf(Triple(1, 2, 2))
  println(networkDelayTime(times, 2, 2))
}
