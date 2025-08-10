package leetcode.graph

/* 10 Aug 2025 15:39 */

/** [The Maze](https://leetcode.ca/2017-04-03-490-The-Maze/) */
fun hasPath(maze: Array<IntArray>, start: IntArray, destination: IntArray): Boolean {
  val nextCells =
    directions
      .map { intArrayOf(it.first + start[0], it.second + start[1]) }
      .filter { isValid(it, maze) }
  return when {
    nextCells.isEmpty() -> start.contentEquals(destination)
    else -> {
      maze[start[0]][start[1]] = 1
      nextCells.any { hasPath(maze, it, destination) }
    }
  }
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

private fun isValid(cell: IntArray, maze: Array<IntArray>): Boolean =
  cell[0] in maze.indices && cell[1] in maze.first().indices && maze[cell[0]][cell[1]] == 0

fun main() {
  println(
    hasPath(
      arrayOf(
        intArrayOf(0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(1, 1, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
      ),
      intArrayOf(0, 4),
      intArrayOf(4, 4),
    )
  )

  println(
    hasPath(
      arrayOf(
        intArrayOf(0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(1, 1, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
      ),
      intArrayOf(0, 4),
      intArrayOf(3, 2),
    )
  )

  println(
    hasPath(
      arrayOf(
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(0, 1, 0, 0, 0),
      ),
      intArrayOf(4, 3),
      intArrayOf(0, 1),
    )
  )
}
