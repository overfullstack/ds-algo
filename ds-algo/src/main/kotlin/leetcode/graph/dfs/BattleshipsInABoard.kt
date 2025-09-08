package leetcode.graph.dfs

/* 01 Aug 2025 11:51 */

fun countBattleships(board: Array<CharArray>): Int =
  board.indices
    .flatMap { row -> board[row].indices.map { col -> row to col } }
    .asSequence()
    .onEach { dftPerGroup(it, board) }
    .count()

val directions2 = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)

fun dftPerGroup(cell: Pair<Int, Int>, board: Array<CharArray>) {
  if (!isValid(cell, board) || isBattleshipPresentAdjacent(cell, board)) {
    return
  }
  board[cell.first][cell.second] = '.' // visited
  directions2
    .asSequence()
    .map { it.first + cell.first to it.second + cell.second }
    .forEach { dftPerGroup(it, board) }
}

private fun isValid(cell: Pair<Int, Int>, board: Array<CharArray>) =
  cell.first in board.indices &&
    cell.second in board.first().indices &&
    board[cell.first][cell.second] == 'X'

private fun isBattleshipPresentAdjacent(cell: Pair<Int, Int>, board: Array<CharArray>) =
  (cell.first > 1 && board[cell.first - 1][cell.second] == 'X') ||
    (cell.second > 1 && board[cell.first][cell.second - 1] == 'X')
