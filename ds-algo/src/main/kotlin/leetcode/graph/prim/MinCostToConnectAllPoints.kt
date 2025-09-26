package leetcode.graph.prim

import java.util.PriorityQueue
import kotlin.math.abs

/* 21 Aug 2025 15:50 */

/**
 * [1584. Min Cost to Connect All
 * Points](https://leetcode.com/problems/min-cost-to-connect-all-points/)
 */
fun minCostConnectPoints(points: Array<IntArray>): Int { // * Prim's MST Algorithm
  if (points.size <= 1) {
    return 0
  }
  val pq = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
  pq.add(0 to 0)
  val visitedIdx = mutableSetOf<Int>()
  var totalCost = 0
  while (pq.isNotEmpty() && visitedIdx.size < points.size) {
    val (idx, cost) = pq.poll() // ! Pick the nearest point to join
    if (idx !in visitedIdx) {
      totalCost += cost
      visitedIdx += idx
      points.indices
        .filter { it !in visitedIdx }
        .forEach {
          val newCost = manhattanDistance(points[idx], points[it])
          pq.add(it to newCost)
        }
    }
  }
  return totalCost
}

private fun manhattanDistance(point1: IntArray, point2: IntArray): Int {
  val (x1, y1) = point1
  val (x2, y2) = point2
  return abs(x1 - x2) + abs(y1 - y2)
}

fun main() {
  println(
    minCostConnectPoints(
      arrayOf(
        intArrayOf(0, 0),
        intArrayOf(2, 2),
        intArrayOf(3, 10),
        intArrayOf(5, 2),
        intArrayOf(7, 0),
      )
    )
  ) // 20
}
