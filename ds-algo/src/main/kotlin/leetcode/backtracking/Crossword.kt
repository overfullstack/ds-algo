package leetcode.backtracking

/* 06 Aug 2025 13:33 */

/**
 * [2018. Check if Word Can Be Placed In
 * Crossword](https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword)
 */
fun placeWordInCrossword(board: Array<CharArray>, word: String): Boolean =
  board.indices
    .asSequence()
    .flatMap { row -> board.first().indices.map { col -> row to col } }
    .any { cell ->
      directions
        .filter {
          val nextCellOppDir = cell.first - it.first to cell.second - it.second
          isInvalidOrOccupied(nextCellOppDir, board)
        }
        .any { direction -> place(cell, direction, board, word) }
    }

fun place(
  cell: Pair<Int, Int>,
  direction: Pair<Int, Int>,
  board: Array<CharArray>,
  word: String,
  wordIndex: Int = 0,
): Boolean =
  when {
    wordIndex == word.lastIndex + 1 -> isInvalidOrOccupied(cell, board) // ! As per problem
    isInvalidOrOccupied(cell, board) -> false
    board[cell.first][cell.second] != ' ' && board[cell.first][cell.second] != word[wordIndex] ->
      false
    else ->
      place(
        cell.first + direction.first to cell.second + direction.second,
        direction,
        board,
        word,
        wordIndex + 1,
      )
  }

private fun isInvalidOrOccupied(cell: Pair<Int, Int>, board: Array<CharArray>): Boolean =
  !isValid(cell, board) || board[cell.first][cell.second] == '#'

fun isValid(cell: Pair<Int, Int>, board: Array<CharArray>): Boolean =
  cell.first in board.indices && cell.second in board[0].indices

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun main() {
  println(
    placeWordInCrossword(
      arrayOf(charArrayOf('#', ' ', '#'), charArrayOf(' ', ' ', '#'), charArrayOf('#', 'c', ' ')),
      "abc",
    )
  )
  println(
    placeWordInCrossword(
      arrayOf(charArrayOf(' ', '#', 'a'), charArrayOf(' ', '#', 'c'), charArrayOf(' ', '#', 'a')),
      "ac",
    )
  )
}
