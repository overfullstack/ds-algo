package educative.heap

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

fun kClosestPointsToOrigin(points: List<Pair<Int, Int>>, k: Int): List<Pair<Int, Int>> {
  val maxHeap = PriorityQueue(Comparator.comparingDouble(::distanceFromOrigin))
  points.take(k).forEach { maxHeap.add(it) }
  points.drop(k).map {
    if (distanceFromOrigin(it) < distanceFromOrigin(maxHeap.peek())) {
      maxHeap.poll()
      maxHeap.add(it)
    }
  }
  return maxHeap.toList()
}

fun distanceFromOrigin(point: Pair<Int, Int>): Double =
  sqrt(point.first.toDouble().pow(2) + point.second.toDouble().pow(2))
