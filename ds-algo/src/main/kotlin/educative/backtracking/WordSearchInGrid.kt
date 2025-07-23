package educative.backtracking

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun wordSearchInGrid(grid: Array<CharArray>, word: String): Boolean {
  return grid.indices.any { row ->
    grid[0]
      .indices
      .asSequence()
      .filter { col -> grid[row][col] == word[0] }
      .any { col -> wordSearchPerBranch(grid, word, row, col, 1) }
  }
}

// * This visited is specific to this DFT originated at this `gridPoint`. No global Visited,
// as search path from other `gridPoints` can overlap depending on the originating cell,
// like nap and snap
private fun wordSearchPerBranch(
  grid: Array<CharArray>,
  word: String,
  row: Int,
  col: Int,
  wordIndex: Int,
  visited: MutableSet<Pair<Int, Int>> = mutableSetOf(row to col),
): Boolean =
  when {
    wordIndex > word.lastIndex -> true // Not equal to, compare the last index also
    else ->
      directions
        .asSequence()
        .map { (x, y) -> row + x to col + y }
        .filter { (r, c) ->
          isValid(r, c, grid) && (r to c) !in visited && grid[r][c] == word[wordIndex]
        }
        .any { (r, c) ->
          wordSearchPerBranch(grid, word, r, c, wordIndex + 1, visited.apply { add(r to c) }).also {
            visited.remove(r to c) // Remove for other searches to use it
          }
        }
  }

private fun isValid(row: Int, col: Int, grid: Array<CharArray>): Boolean =
  row in grid.indices && col in grid[0].indices
