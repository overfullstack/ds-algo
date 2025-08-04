package leetcode.dp

/* 04 Aug 2025 07:48 */

/**
 * [562. Longest Line of Consecutive One in
 * Matrix](https://leetcode.ca/2017-06-14-562-Longest-Line-of-Consecutive-One-in-Matrix/)
 */
fun longestLine(mat: Array<IntArray>): Int {
  val rows = mat.size
  val cols = mat.first().size

  // ! +2 for cols as we need padding on left (for first col) and right (for last col)
  // ! as we do both `col-1` and `col+1`
  val top = Array(rows + 1) { IntArray(cols + 2) }
  val bottom = Array(rows + 1) { IntArray(cols + 2) }
  val diagonal = Array(rows + 1) { IntArray(cols + 2) }
  val antiDiagonal = Array(rows + 1) { IntArray(cols + 2) }
  var max = 0
  for (row in 1..rows) {
    for (col in 1..cols) {
      if (mat[row - 1][col - 1] == 1) {
        top[row][col] = top[row - 1][col]
        bottom[row][col] = bottom[row + 1][col]
        diagonal[row][col] = diagonal[row - 1][col - 1]
        antiDiagonal[row][col] = antiDiagonal[row - 1][col + 1]
        max = maxOf(top[row][col], bottom[row][col], diagonal[row][col], antiDiagonal[row][col])
      }
    }
  }
  return max
}
