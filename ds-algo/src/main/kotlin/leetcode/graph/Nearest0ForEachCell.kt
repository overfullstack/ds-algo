package leetcode.graph

/** [01-matrix](https://leetcode.com/problems/01-matrix/) */
fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
  for (row in 0..matrix.lastIndex) {
    for (col in 0..matrix[0].lastIndex) {
      if (matrix[row][col] > 0) {
        val fromUp = if (row > 0) matrix[row - 1][col] else Int.MAX_VALUE - 999
        val fromLeft = if (col > 0) matrix[row][col - 1] else Int.MAX_VALUE - 999
        matrix[row][col] = minOf(fromUp, fromLeft) + 1
      }
    }
  }
  for (row in matrix.lastIndex downTo 0) {
    for (col in matrix[0].lastIndex downTo 0) {
      if (matrix[row][col] > 0) {
        val fromBottom = if (row < matrix.lastIndex) matrix[row + 1][col] else Int.MAX_VALUE - 999
        val fromRight = if (col < matrix[0].lastIndex) matrix[row][col + 1] else Int.MAX_VALUE - 999
        matrix[row][col] = minOf(matrix[row][col], fromBottom + 1, fromRight + 1)
      }
    }
  }
  return matrix
}

fun updateMatrix2(matrix: Array<IntArray>): Array<IntArray> {
  val queue = ArrayDeque<Pair<Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(row to col)
        1 -> matrix[row][col] = Int.MAX_VALUE // To let our path flow only through 1s.
      }
    }
  }
  while (queue.isNotEmpty()) { // * BFS from 0s to 1s
    val gridPoint = queue.removeFirst()
    val nextDistance = matrix[gridPoint.first][gridPoint.second] + 1
    queue.addAll(
      directions
        .asSequence()
        .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
        .filter { it.isValid(matrix, nextDistance) }
        .onEach { matrix[it.first][it.second] = nextDistance }
    )
  }
  return matrix
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun Pair<Int, Int>.isValid(matrix: Array<IntArray>, distance: Int) =
  first in matrix.indices &&
    second in matrix[0].indices &&
    // * 1. Cells with 0s never pass this as distance is always positive, so 0s are never
    // overridden.
    // * 2. For Storing min distance,
    // If a 0 is totally surrounded by 0s, it's useless as it can never be the nearest 0 for any 1,
    // so skip it.
    distance < matrix[first][second]

fun updateMatrix3(matrix: Array<IntArray>): Array<IntArray> { // BFS
  val queue = ArrayDeque<Pair<Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(row to col)
        1 -> matrix[row][col] = -1 // To differentiate between steps=1 and value 1
      }
    }
  }
  var steps = 0
  while (queue.isNotEmpty()) {
    steps++
    val size = queue.size
    repeat(size) {
      val gridPoint = queue.removeFirst()
      directions
        .asSequence()
        .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
        .filter { it.isValid2(matrix) }
        .forEach {
          matrix[it.first][it.second] = steps
          // all 0s are already in the queue. Let's say a 1 might be totally covered with 1s,
          // all those surrounding 1s need to be crossed to reach such. So adding all those 1s to
          // queue
          queue.add(it)
        }
    }
  }
  return matrix
}

private fun Pair<Int, Int>.isValid2(matrix: Array<IntArray>) =
  first in matrix.indices && second in matrix[0].indices && matrix[first][second] == -1
