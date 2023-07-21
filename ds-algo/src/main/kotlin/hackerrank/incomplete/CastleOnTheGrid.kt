package hackerrank.graphs

val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

@kotlin.ExperimentalStdlibApi
fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
  if (grid.isEmpty()) {
    return 0
  }
  val charGrid = grid.map { it.toCharArray() }
  var steps = 0
  val queue = ArrayDeque<Pair<Int, Int>>()
  queue.add(startX to startY)
  while (queue.isNotEmpty()) {
    steps++
    val size = queue.size
    repeat(size) {
      val gridPoint = queue.removeFirst()
      directions
        .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
        .filter { it.isValid(charGrid) }
        .forEach {
          if (it == (goalX to goalY)) {
            return steps
          }
          charGrid[it.first][it.second] = 'X'
          queue.add(it.first to it.second)
        }
    }
  }
  return -1
}

private fun Pair<Int, Int>.isValid(grid: List<CharArray>) =
  first >= 0 &&
    first <= grid.lastIndex &&
    second >= 0 &&
    second <= grid[0].lastIndex &&
    grid[first][second] != 'X'
