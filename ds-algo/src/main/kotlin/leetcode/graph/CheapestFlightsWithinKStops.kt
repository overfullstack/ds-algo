package leetcode.graph

import ds.graph.EdgeWeightedDiGraph
import java.util.PriorityQueue
import utils.toTriple

/* 05 Aug 2025 15:57 */

/**
 * [787. Cheapest Flights Within K
 * Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) ⏰ TLE
 */
fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
  val edgeWeightedDiGraph: EdgeWeightedDiGraph<Int> =
    EdgeWeightedDiGraph(flights.map { it.toTriple() })
  val pq = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.second })
  pq.add(Triple(src, 0, k))
  var minDistance = Int.MAX_VALUE
  while (pq.isNotEmpty()) {
    val (from, priceFromSource, k) = pq.poll()
    // ! Don't terminate early, allow all paths to be processed
    if (from == dst) {
      minDistance = minOf(minDistance, priceFromSource)
    }
    if (k >= 0) {
      edgeWeightedDiGraph[from]?.forEach { (to, priceFromToTo) ->
        val nextPrice = priceFromSource + priceFromToTo
        // * We just need to prune the destination reaching edges.
        // ! Any edge beyond minDistance can't lead to destination faster than already recorded
        // * Others stay towards the end of pq and get ignored as they dequeue
        if (nextPrice < minDistance) {
          pq.add(Triple(to, nextPrice, k - 1))
        }
      }
    }
  }
  return if (minDistance == Int.MAX_VALUE) -1 else minDistance
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
  ) // 11
}
