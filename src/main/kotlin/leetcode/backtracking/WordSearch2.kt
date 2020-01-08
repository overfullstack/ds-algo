/* gakshintala created on 12/27/19 */
package leetcode.backtracking

import ds.Trie

fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val trie = Trie().also { words.toSet().forEach { word -> it.insert(word) } }
    return board.indices.fold(emptyList()) { results, row ->
        results + board[0].indices.flatMap { col ->
            (trie.children[board[row][col] - 'a']?.let { findWords(
                board,
                it,
                row to col
            ).toList() } ?: emptyList())
                .also { it.forEach { word -> trie.remove(word) } }
        }
    }
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun findWords(
    board: Array<CharArray>,
    trie: Trie?,
    gridPoint: Pair<Int, Int>,
    visited: Array<BooleanArray> = Array(board.size) { BooleanArray(board[0].size) }
): Set<String> {
    if (trie == null || !gridPoint.isValid(board, visited)) {
        return emptySet()
    }

    visited[gridPoint.first][gridPoint.second] = true
    val wordsFound =
        // trie.word can be erased after appending to results, but that doesn't save any iterations, so went with set.
        directions.fold(if (trie.isEnd) setOf(trie.word) else emptySet()) { wordsFound, direction ->
            val nextGridPoint = (gridPoint.first + direction.first) to (gridPoint.second + direction.second)
            wordsFound + if (nextGridPoint.isValid(board, visited)) {
                findWords(
                    board,
                    trie.children[board[nextGridPoint.first][nextGridPoint.second] - 'a'],
                    nextGridPoint,
                    visited
                )
            } else {
                emptySet()
            }
        }
    visited[gridPoint.first][gridPoint.second] = false
    return wordsFound
}

private fun Pair<Int, Int>.isValid(
    board: Array<CharArray>,
    visited: Array<BooleanArray>
) = first >= 0 && second >= 0
        && first < board.size && second < board[0].size
        && !visited[first][second]