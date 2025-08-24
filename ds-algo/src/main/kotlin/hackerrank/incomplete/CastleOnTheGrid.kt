package hackerrank.incomplete

/** [Castle on the Grid](https://www.hackerrank.com/challenges/castle-on-the-grid/) */
fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
  if (grid.isEmpty()) {
    return 0
  }
  val visited = mutableSetOf<Pair<Int, Int>>()
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  queue.add(Triple(startX, startY, 0))
  visited += (startX to startY)
  while (queue.isNotEmpty()) {
    val (x, y, distance) = queue.removeFirst()
    for ((dx, dy) in directions) {
      var nextX = x + dx
      var nextY = y + dy
      // ! Not marking cells with `X` for visited, as it creates artificial blocks
      while (isValid(nextX, nextY, grid) && grid[nextX][nextY] != 'X') {
        val nextCell = nextX to nextY
        // ! Catch goal ahead for optimization. This allows on-the-way goal
        if (nextX == goalX && nextY == goalY) {
          return distance + 1
        }
        if (nextCell !in visited) {
          // ! Optimization to avoid duplicates in queue to avoid Cross-Direction Interference
          visited += nextCell
          queue.add(Triple(nextX, nextY, distance + 1))
        }
        // ! Move ahead in the same direction until valid
        nextX += dx
        nextY += dy
      }
    }
  }
  return -1
}

private fun isValid(x: Int, y: Int, grid: Array<String>) = x in grid.indices && y in grid[0].indices

val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

fun main() {
  println(minimumMoves(arrayOf(".X.", ".X.", "..."), 0, 0, 0, 2)) // 3
  println(
    minimumMoves(
      arrayOf(
        ".X..XX...X",
        "X.........",
        ".X.......X",
        "..........",
        "........X.",
        ".X...XXX..",
        ".....X..XX",
        ".....X.X..",
        "..........",
        ".....X..XX",
      ),
      9,
      1,
      9,
      6,
    )
  ) // 3
}
