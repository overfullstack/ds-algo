package leetcode.graph

/** https://leetcode.com/problems/01-matrix/ */
fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> { // DFS
  val queue = ArrayDeque<Pair<Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(row to col)
        1 -> matrix[row][col] = Int.MAX_VALUE // To let our path only flow through 1s.
      }
    }
  }
  while (queue.isNotEmpty()) { // * BFS from 0s to 1s
    val gridPoint = queue.removeFirst()
    val nextDistance = matrix[gridPoint.first][gridPoint.second] + 1
    directions
      .asSequence()
      .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
      .filter { it.isValid(matrix, nextDistance) }
      .forEach {
        matrix[it.first][it.second] = nextDistance
        queue.add(it)
      }
  }
  return matrix
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun Pair<Int, Int>.isValid(matrix: Array<IntArray>, distance: Int) =
  first in matrix.indices && second in matrix[0].indices &&
    // * 1. Cells with 0s never pass this as distance is always positive, so 0s are never overriden.
    // * 2. For Storing min distance
    // If a 0 is totally surround by 0s, it's useless as it can never be a nearst 0 for any 1, so
    // skip it.
    distance < matrix[first][second]

fun updateMatrix2(matrix: Array<IntArray>): Array<IntArray> { // BFS
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
