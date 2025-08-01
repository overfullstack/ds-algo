package educative.backtracking

fun nQueens(n: Int, curRow: Int = 0, queenCells: Map<Int, Int> = emptyMap()): Int =
  // * Find a column for each row. After a combination is found, backtrack and
  // place it in the next column and try a new combination
  when (curRow) {
    n -> 1
    else ->
      (0 until n)
        .asSequence()
        .filter { col -> isValid(curRow, col, queenCells) }
        .map { col -> nQueens(n, curRow + 1, queenCells + (curRow to col)) }
        .sum()
  }

private fun isValid(row: Int, col: Int, queenCells: Map<Int, Int>): Boolean =
  queenCells.all { (queenRow, queenCol) ->
    // * If removing row equates cols, that means they both are seperated by same rows and cols
    // * which proves they are in same diagonal
    val diagonalOffset = row - queenRow
    col != queenCol && col != queenCol - diagonalOffset && col != queenCol + diagonalOffset
  }
