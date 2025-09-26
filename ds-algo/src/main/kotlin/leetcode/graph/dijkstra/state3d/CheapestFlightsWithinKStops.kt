package leetcode.graph.dijkstra.state3d

import ds.graph.EdgeWeightedDiGraph
import java.util.PriorityQueue

/* 05 Aug 2025 15:57 */

/**
 * [787. Cheapest Flights Within K
 * Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
 */
fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
  val edgeWeightedDiGraph: EdgeWeightedDiGraph<Int> = EdgeWeightedDiGraph(flights)
  val pq = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.second })
  val start = Triple(src, 0, k + 1)
  // ! This works even without `visited`, but it helps perf, by pruning duplicate states
  val visited = mutableSetOf(start)
  pq.add(start) // ! `k + 1` including the current stop
  while (pq.isNotEmpty()) {
    val (from, priceFromSource, remainingStops) = pq.poll()
    if (from == dst) {
      return priceFromSource
    }
    if (remainingStops > 0) { // ! `> 0` as we enqueued `k + 1` for src
      edgeWeightedDiGraph[from]
        ?.asSequence()
        ?.map { (to, priceFromToTo) ->
          Triple(to, priceFromSource + priceFromToTo, remainingStops - 1)
        }
        // ! No tracking of global `priceFromSource` and no check `< curPriceFromSource`
        // ! as we let all the possible paths to be enqueued in the ascending order of
        // ! `priceFromSource` and the first path with `remainingStops > 0` reaches the destination
        ?.filter { it !in visited }
        ?.forEach {
          // ! Spl Visit-on-Enqueue as (src, price, remainingStops) combo is always optimal on the
          // ! first visit. Note this is uncommon in a Dijkstra or BFS
          // ! It's more efficient as it avoids bloating `pq` with duplicate entries
          // ! It works here as it tracks a combo and not just `node`
          visited += it
          pq.add(it)
        }
    }
  }
  return -1
}

fun main() {
  println(
    findCheapestPrice(
      4,
      arrayOf(
        intArrayOf(0, 1, 100),
        intArrayOf(1, 2, 100),
        intArrayOf(2, 0, 100),
        intArrayOf(1, 3, 600),
        intArrayOf(2, 3, 200),
      ),
      0,
      3,
      1,
    )
  ) // 700
  println(
    findCheapestPrice(
      3,
      arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(0, 2, 500)),
      0,
      2,
      1,
    )
  ) // 200
  println(
    findCheapestPrice(
      11,
      arrayOf(
        intArrayOf(0, 3, 3),
        intArrayOf(3, 4, 3),
        intArrayOf(4, 1, 3),
        intArrayOf(0, 5, 1),
        intArrayOf(5, 1, 100),
        intArrayOf(0, 6, 2),
        intArrayOf(6, 1, 100),
        intArrayOf(0, 7, 1),
        intArrayOf(7, 8, 1),
        intArrayOf(8, 9, 1),
        intArrayOf(9, 1, 1),
        intArrayOf(1, 10, 1),
        intArrayOf(10, 2, 1),
        intArrayOf(1, 2, 100),
      ),
      0,
      2,
      4,
    )
  )
}
