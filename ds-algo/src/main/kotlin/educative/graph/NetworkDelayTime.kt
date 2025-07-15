package educative.graph

import ds.graph.EdgeWeightedDiGraph
import ds.graph.EdgeWeightedDiGraph.WeightedEdge
import java.util.PriorityQueue

/* 15 Jul 2025 15:41 */

fun networkDelayTime(times: List<Triple<Int, Int, Int>>, n: Int, origin: Int): Int {
  val graph = EdgeWeightedDiGraph(times)
  // ! We follow greedy approach here. This has to be sorted by weight, 
  // so we reach neighbours through the shortest path
  val pq = PriorityQueue(Comparator.comparingInt<WeightedEdge<Int>> { it.weight })
  pq.add(WeightedEdge(origin, 0))
  val visited = mutableSetOf<Int>()
  var minDelay = Int.MIN_VALUE
  while (pq.isNotEmpty()) {
    val (to, time) = pq.poll()
    // This if check deals with transitive duplicates that get added to the queue before they are
    // visited
    if (to !in visited) {
      visited.add(to)
      minDelay = maxOf(minDelay, time)
      graph[to]
        ?.filter { (to, _) -> to !in visited }
        ?.forEach { (to, time) -> pq.add(WeightedEdge(to, time + minDelay)) }
    }
  }
  return if (visited.size == n) minDelay else -1
}

fun main() {
  val times = listOf(Triple(1, 2, 2))
  println(networkDelayTime(times, 2, 2))
}
