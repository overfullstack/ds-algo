package educative.backtracking

/** [51. N-Queens](https://leetcode.com/problems/n-queens/) */
fun solveNQueens(
  n: Int,
  curRow: Int = 0,
  queenCells: Map<Int, Int> = emptyMap(),
): List<List<String>> =
  // * For a row, find a column and recurse for next row.
  // * After a combination is found, backtrack and place it in the next column for new combination
  when (curRow) {
    n -> listOf(generateBoard(n, queenCells))
    else ->
      (0 until n)
        .asSequence()
        .filter { col -> isValid(curRow, col, queenCells) }
        .flatMap { col -> solveNQueens(n, curRow + 1, queenCells + (curRow to col)) }
        .toList()
  }

fun generateBoard(n: Int, queenCells: Map<Int, Int>): List<String> =
  (0..<n).map { row -> String(CharArray(n) { col -> if (queenCells[row] == col) 'Q' else '.' }) }

private fun isValid(row: Int, col: Int, queenCells: Map<Int, Int>): Boolean =
  queenCells.all { (queenRow, queenCol) ->
    // * If removing row equates cols, that means they both are seperated by same rows and cols
    // * which proves they are in same diagonal
    val diagonalOffset = row - queenRow
    col != queenCol && (col != queenCol - diagonalOffset) && (col != queenCol + diagonalOffset)
  }

// ! Only deals with the combination count
fun nQueensCombinationCount(n: Int, curRow: Int = 0, queenCells: Map<Int, Int> = emptyMap()): Int =
  // * For a row, find a column and recurse for next row.
  // * After a combination is found, backtrack and place it in the next column for new combination
  when (curRow) {
    n -> 1
    else ->
      (0 until n)
        .asSequence()
        .filter { col -> isValid(curRow, col, queenCells) }
        .sumOf { col -> nQueensCombinationCount(n, curRow + 1, queenCells + (curRow to col)) }
  }

fun main() {
  println(nQueensCombinationCount(4))
}
