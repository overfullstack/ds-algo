/* gakshintala created on 12/27/19 */
package leetcode.backtracking

import ds.TrieNode

fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
  val trie = TrieNode().apply { words.toSet().forEach { word -> insert(word) } }
  return board.indices.flatMap { row ->
    board[0].indices.flatMap { col ->
      (trie.children[board[row][col] - 'a']?.findWords(board, row to col) ?: emptySet()).also {
        it.forEach { word -> trie.remove(word) }
      }
    }
  }
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun TrieNode.findWords(
  board: Array<CharArray>,
  gridPoint: Pair<Int, Int>,
  visited: Array<BooleanArray> =
    Array(board.size) { BooleanArray(board[0].size) }
      .apply {
        this[gridPoint.first][gridPoint.second] = true
      } // * This visited is specific to this DFT originated at this `gridPoint`. No global Visited,
  // as search from other `gridPoints` can overlap.
): Set<String> =
  // To avoid duplicates, trie.word can be erased after appending to results, but that doesn't save
  // any iterations, so went with set.
  directions
    .asSequence()
    .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
    .filter { it.isValid(board, visited) }
    .fold(if (isEnd) setOf(word) else emptySet()) { wordsFound, nextGridPoint ->
      // Not accumulating word, as trie takes care of it, we just need to ask for `word` at the end.
      wordsFound +
        (children[board[nextGridPoint.first][nextGridPoint.second] - 'a']?.findWords(
          board,
          nextGridPoint,
          visited.apply { this[nextGridPoint.first][nextGridPoint.second] = true }
        ) ?: emptySet())
    }
    .also {
      visited[gridPoint.first][gridPoint.second] = false
    } // backtracking visited in this branch as we unwind this DFT.

private fun Pair<Int, Int>.isValid(board: Array<CharArray>, visited: Array<BooleanArray>) =
  first in board.indices && second in board[0].indices && !visited[first][second]
