package hackerrank.graphs

/** [Castle on the Grid](https://www.hackerrank.com/challenges/castle-on-the-grid/) */
fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
  if (grid.isEmpty()) { // * Pure BFS
    return 0
  }
  val visited = mutableSetOf<Pair<Int, Int>>()
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  queue.add(Triple(startX, startY, 0))
  visited += startX to startY
  while (queue.isNotEmpty()) {
    val (row, col, distance) = queue.removeFirst()
    for ((dr, dc) in directions) {
      var nextRow = row + dr
      var nextCol = col + dc
      // ! Move ahead in the same direction until valid
      while (isValid(nextRow, nextCol, grid) && grid[nextRow][nextCol] != 'X') {
        val nextCell = nextRow to nextCol
        // ! Catch goal ahead for optimization. This allows on-the-way goal
        if (nextRow == goalX && nextCol == goalY) {
          return distance + 1
        }
        if (nextCell !in visited) {
          visited += nextCell // * Visit-on-Enqueue for pure BFS
          queue.add(Triple(nextRow, nextCol, distance + 1))
        }
        nextRow += dr
        nextCol += dc
      }
    }
  }
  return -1
}

private fun isValid(row: Int, col: Int, grid: Array<String>) =
  row in grid.indices && col in grid[0].indices

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
