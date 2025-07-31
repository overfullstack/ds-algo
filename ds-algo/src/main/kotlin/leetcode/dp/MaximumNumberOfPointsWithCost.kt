package leetcode.dp

/* 30 Jul 2025 23:12 */

fun maxPoints(points: Array<IntArray>): Long {
  val n = points[0].size

  var currentRowMaxPoints = LongArray(n)

  val inf = 1L shl 60

  for (row in points) {
    val nextRowMaxPoints = LongArray(n)

    var leftMax = -inf
    var rightMax = -inf

    for (j in 0..<n) {
      leftMax = maxOf(leftMax, currentRowMaxPoints[j] + j)
      nextRowMaxPoints[j] = maxOf(nextRowMaxPoints[j], row[j] + leftMax - j)
    }

    for (j in n - 1 downTo 0) {
      rightMax = maxOf(rightMax, currentRowMaxPoints[j] - j)
      nextRowMaxPoints[j] = maxOf(nextRowMaxPoints[j], row[j] + rightMax + j)
    }

    currentRowMaxPoints = nextRowMaxPoints
  }
  return currentRowMaxPoints.max()
}

fun main() {
  println(maxPoints(arrayOf(intArrayOf(1, 2, 3), intArrayOf(1, 5, 1), intArrayOf(3, 1, 1))))
}
