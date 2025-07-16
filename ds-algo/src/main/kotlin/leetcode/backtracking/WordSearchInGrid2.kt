/* gakshintala created on 12/27/19 */
package leetcode.backtracking

import ds.TrieNode

fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
  val trie = TrieNode().apply { words.toSet().forEach { word -> insert(word) } }
  return board.indices.flatMap { row ->
    board[0].indices.flatMap { col ->
      // * trie is driving the search
      trie.children[board[row][col] - 'a']?.findWords(board, row, col)?.also {
        it.forEach { word -> trie.remove(word) }
      } ?: emptySet()
    }
  }
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

// * This visited is specific to this DFT originated at this `gridPoint`. No global Visited,
// as search from other `gridPoints` can overlap.
private fun TrieNode.findWords(
  board: Array<CharArray>,
  row: Int,
  col: Int,
  visited: MutableSet<Pair<Int, Int>> = mutableSetOf(row to col),
): Set<String> =
  (if (isEnd) setOf(word) else emptySet()) +
    directions
      .asSequence()
      .map { (row + it.first) to (col + it.second) }
      .filter { it !in visited && it.isValid(board) }
      .flatMap { (row, col) ->
        children[board[row][col] - 'a']
          ?.findWords(board, row, col, visited.apply { add(row to col) })
          ?.also { visited.remove(row to col) } ?: emptySet()
      }

private fun Pair<Int, Int>.isValid(board: Array<CharArray>) =
  first in board.indices && second in board[0].indices
