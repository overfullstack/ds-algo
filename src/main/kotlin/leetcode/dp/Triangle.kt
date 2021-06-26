package leetcode.dp

/**
 * https://leetcode.com/problems/triangle/
 * Find min path from bottom to top in a triangle.
 */
fun minimumTotal(triangle: List<List<Int>>): Int {
  val table = IntArray(triangle.size + 1)

  for (row in triangle.lastIndex downTo 0) {
    for (col in 0..row) {
      table[col] = minOf(table[col], table[col + 1]) + triangle[row][col]
    }
  }
  return table[0]
}
