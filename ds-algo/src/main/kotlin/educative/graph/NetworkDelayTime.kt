package educative.graph

import ds.graph.EdgeWeightedDiGraph
import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.PriorityQueue

/* 15 Jul 2025 15:41 */

fun networkDelayTime(times: List<Triple<Int, Int, Int>>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph(times)
  val pq = PriorityQueue(Comparator.comparingInt<WeightedEdge<Int>> { it.weight })
  pq.add(WeightedEdge(origin, 0))
  val visited = mutableSetOf<Int>()
  var minDelay = Int.MIN_VALUE
  while (pq.isNotEmpty()) {
    // ! PriorityQueue poll returns the shortest edge
    val (node, time) = pq.poll()
    if (node !in visited) {
      visited.add(node)
      minDelay = maxOf(minDelay, time)
      graph[node]
        ?.filter { (to, _) -> to !in visited }
        ?.forEach { (to, timeFromNodeToTo) ->
          pq.add(WeightedEdge(to, timeFromNodeToTo + minDelay))
        }
    }
  }
  return if (visited.size == n) minDelay else -1
}

fun main() {
  val times = listOf(Triple(1, 2, 2))
  println(networkDelayTime(times, 2, 2))
}
