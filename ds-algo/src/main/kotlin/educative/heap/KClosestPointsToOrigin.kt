package educative.heap

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

fun kClosestPointsToOrigin(points: List<Pair<Int, Int>>, k: Int): Set<Pair<Int, Int>> {
  // ! Make Fathest points vulnerable to polling
  val maxHeap = PriorityQueue(Comparator.comparingDouble(::distanceFromOrigin).reversed())
  for (point in points) {
    maxHeap.add(point)
    if (maxHeap.size > k) {
      maxHeap.poll()
    }
  }
  return maxHeap.toSet()
}

fun distanceFromOrigin(point: Pair<Int, Int>): Double =
  sqrt(point.first.toDouble().pow(2) + point.second.toDouble().pow(2))
