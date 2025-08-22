package leetcode.array

import kotlin.math.abs

/* 21 Aug 2025 18:53 */

/** [2013. Detect Squares](https://leetcode.com/problems/detect-squares/) */
class DetectSquares() {

  val pointCount = mutableMapOf<Pair<Int, Int>, Int>()

  fun add(point: IntArray) {
    pointCount.merge(point[0] to point[1], 1, Int::plus)
  }

  fun count(point: IntArray): Int {
    val (x, y) = point
    return pointCount.keys
      .filter { (xd, yd) -> abs(x - xd) != 0 && abs(x - xd) == abs(y - yd) }
      .sumOf { (xd, yd) ->
        (pointCount[xd to yd] ?: 0) * (pointCount[xd to y] ?: 0) * (pointCount[x to yd] ?: 0)
      }
  }
}
