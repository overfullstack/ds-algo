package educative.backtracking

fun nQueens(n: Int, curRow: Int = 0, queenCells: Map<Int, Int> = emptyMap()): Int =
  when (curRow) {
    n -> 1
    else ->
      (0 until n)
        .asSequence()
        .filter { col -> isValid(curRow, col, queenCells) }
        .map { col -> nQueens(n, curRow + 1, queenCells + (curRow to col)) }
        .sum()
  }

private fun isValid(proposedRow: Int, proposedCol: Int, queenCells: Map<Int, Int>): Boolean =
  queenCells.all { (queenRow, queenCol) ->
    val diagonalOffset = proposedRow - queenRow
    proposedCol != queenCol &&
      proposedCol != queenCol - diagonalOffset &&
      proposedCol != queenCol + diagonalOffset
  }
