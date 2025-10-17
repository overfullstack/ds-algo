package educative.backtracking

/** [79. Word Search](https://leetcode.com/problems/word-search) */
fun wordSearchInGrid(board: Array<CharArray>, word: String): Boolean =
  board.indices.any { row ->
    board[0]
      .indices
      .asSequence()
      .filter { col -> board[row][col] == word[0] }
      .any { col -> wordSearchPerBranch(board, word, row, col, 1) }
  }

// * This visited is specific to this DFT originated at this `gridPoint`. No global Visited,
// as search path from other `gridPoints` can overlap depending on the originating cell,
// like nap and snap
private fun wordSearchPerBranch(
  board: Array<CharArray>,
  word: String,
  row: Int,
  col: Int,
  wordIndex: Int,
  visited: MutableSet<Pair<Int, Int>> = mutableSetOf(row to col),
): Boolean =
  when {
    wordIndex == word.lastIndex + 1 -> true
    else ->
      directions
        .asSequence()
        .map { (x, y) -> row + x to col + y }
        .filter { (r, c) ->
          isValid(r, c, board) && (r to c) !in visited && board[r][c] == word[wordIndex]
        }
        .any { (r, c) ->
          wordSearchPerBranch(board, word, r, c, wordIndex + 1, visited.apply { add(r to c) })
            .also {
              visited.remove(r to c) // ! Backtrack, remove for other searches to use it
            }
        }
  }

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

private fun isValid(row: Int, col: Int, grid: Array<CharArray>): Boolean =
  row in grid.indices && col in grid[0].indices
