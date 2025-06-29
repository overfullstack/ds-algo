package educative.backtracking

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun wordSearch(grid: Array<CharArray>, word: String): Boolean {
  val wordStartIndices = grid.indices.flatMap { row ->
    grid[0].indices.filter { col -> grid[row][col] == word[0] }.map {col -> row to col }
   }
  val visited = Array(grid.size) { BooleanArray(grid[0].size) }
  return wordStartIndices.asSequence().any { (r, c) -> 
    visited[r][c] = true
    directions.asSequence().map { (x, y) -> r + x to c + y }
    .filter { (r, c) -> isValid(r, c, grid) && !visited[r][c] && grid[r][c] == word[1] }
    .any { (r, c) -> wordSearchInternal(grid, word, r, c, 2, visited.apply { this[r][c] = true }).also { visited[r][c] = false } }
  }
}

private fun wordSearchInternal(grid: Array<CharArray>, word: String, r: Int, c: Int, wordIndex: Int, visited: Array<BooleanArray>): Boolean =
  if (wordIndex > word.lastIndex) {
    true
  } else {
    directions.asSequence().map { (x, y) -> r + x to c + y }
    .filter { (r, c) -> isValid(r, c, grid) && !visited[r][c] && grid[r][c] == word[wordIndex] }
    .any { (r, c) -> wordSearchInternal(grid, word, r, c, wordIndex + 1, visited.apply { this[r][c] = true }).also { visited[r][c] = false } }
  }

private fun isValid(row: Int, col: Int, grid: Array<CharArray>): Boolean = 
  row in grid.indices && col in grid[0].indices
