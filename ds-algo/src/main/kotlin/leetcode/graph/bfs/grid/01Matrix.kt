package leetcode.graph.bfs.grid

/** [01-matrix](https://leetcode.com/problems/01-matrix/) Nearest 0 */
fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> { // * BFS
  // ! Using normal queue instead of pq, as we don't have variable weights
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(Triple(row, col, 0))
        1 -> matrix[row][col] = -1 // ! To differentiate between distance=1 and value 1
      }
    }
  }
  while (queue.isNotEmpty()) {
    val (row, col, distance) = queue.removeFirst()
    val nextDistance = distance + 1
    directions // ! Deeper 0s are auto-filtered as they can't find `-1` in any direction
      .asSequence()
      .map { row + it.first to col + it.second }
      .filter { (nextRow, nextCol) ->
        // ! Using visited instead of distance check, as with unit weights
        // ! first visit is always the optimal and BFS naturally finds the shortest paths
        isValid(nextRow to nextCol, matrix) && matrix[nextRow][nextCol] == -1
      }
      .forEach { (nextRow, nextCol) ->
        matrix[nextRow][nextCol] = nextDistance // ! Visit-on-Enqueue for BFS
        // All 0s are already in the queue. Let's say a 1 might be totally covered with 1s,
        // all those surrounding 1s need to be crossed to reach such.
        // ! So adding all those 1s to queue to go from border into the island depth
        queue.add(Triple(nextRow, nextCol, nextDistance))
      }
  }
  return matrix
}

private fun isValid(cell: Pair<Int, Int>, matrix: Array<IntArray>) =
  cell.first in matrix.indices && cell.second in matrix[0].indices

fun updateMatrixLevelTracking(matrix: Array<IntArray>): Array<IntArray> { // BFS
  val queue = ArrayDeque<Pair<Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(row to col)
        1 -> matrix[row][col] = -1 // ! To differentiate between distance=1 and value 1
      }
    }
  }
  var distance = 0
  while (queue.isNotEmpty()) {
    distance++
    val size = queue.size
    repeat(size) {
      val cell = queue.removeFirst()
      directions
        .asSequence()
        .map { (cell.first + it.first) to (cell.second + it.second) }
        .filter { isValid(it, matrix) && matrix[it.first][it.second] == -1 }
        .forEach {
          matrix[it.first][it.second] = distance // ! This also serves as visited
          // all 0s are already in the queue. Let's say a 1 might be totally covered with 1s,
          // all those surrounding 1s need to be crossed to reach such.
          // ! So adding all those 1s to queue
          queue.add(it)
        }
    }
  }
  return matrix
}

fun updateMatrix2(matrix: Array<IntArray>): Array<IntArray> {
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

fun updateMatrix3(matrix: Array<IntArray>): Array<IntArray> {
  val queue = ArrayDeque<Pair<Int, Int>>()
  for (row in matrix.indices) {
    for (col in matrix[0].indices) {
      when (matrix[row][col]) {
        0 -> queue.add(row to col)
        1 -> matrix[row][col] = Int.MAX_VALUE // ! distance to be overridden by BFS min distance
      }
    }
  }
  while (queue.isNotEmpty()) { // * BFS from 0s to 1s
    val cell = queue.removeFirst()
    val nextDistance = matrix[cell.first][cell.second] + 1
    queue.addAll(
      directions
        .asSequence()
        .map { (cell.first + it.first) to (cell.second + it.second) }
        // ! Ony 0 adjacent to 1 carry a distance. That gets carry forward to next 1s
        // ! 0s surround by 0s stay 0 as they don't pass this
        .filter { isValid2(it, matrix) && nextDistance < matrix[it.first][it.second] }
        .onEach { matrix[it.first][it.second] = nextDistance }
    )
  }
  return matrix
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun isValid2(cell: Pair<Int, Int>, matrix: Array<IntArray>) =
  cell.first in matrix.indices && cell.second in matrix.first().indices
