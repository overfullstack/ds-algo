package leetcode.dp

/* 30 Jul 2025 23:12 */

/**
 * [1937. Maximum Number of Points with
 * Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost)
 */
fun maxPoints(points: Array<IntArray>): Long {
  val noOfCols = points.first().size
  var prev = LongArray(noOfCols)
  for (col in points.first().indices) {
    prev[col] = points[0][col].toLong()
  }
  for (row in 0 until points.lastIndex) {
    val left = LongArray(noOfCols)
    left[0] = prev[0]
    for (col in 1..points.first().lastIndex) {
      left[col] = maxOf(prev[col], left[col - 1] - 1)
    }
    val right = LongArray(noOfCols)
    right[right.lastIndex] = prev[prev.lastIndex]
    for (col in (points.first().lastIndex - 1) downTo 0) {
      right[col] = maxOf(prev[col], right[col + 1] - 1)
    }
    val cur = LongArray(noOfCols)
    for (col in points.first().indices) {
      cur[col] = points[row + 1][col] + maxOf(left[col], right[col])
    }
    prev = cur
  }
  return prev.max() // ! using prev to handle points with only one row
}

fun main() {
  println(maxPoints(arrayOf(intArrayOf(1, 2, 3), intArrayOf(1, 5, 1), intArrayOf(3, 1, 1))))
}
