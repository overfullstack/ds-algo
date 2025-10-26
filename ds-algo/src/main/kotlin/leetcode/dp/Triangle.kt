package leetcode.dp

/** [120. Triangle](https://leetcode.com/problems/triangle/) */
fun minimumTotal(triangle: List<List<Int>>): Int {
  val table = triangle.last().toIntArray()
  // ! Calculating from triangle bottom to top.
  // ! It can be solved from top to bottom also, but need to tackle edge cases 0th and last col
  for (row in triangle.lastIndex - 1 downTo 0) {
    for (col in 0..row) {
      // ! Climb from min of prev `col` or `col + 1`, add current cost
      table[col] = minOf(table[col], table[col + 1]) + triangle[row][col]
    }
  }
  return table[0]
}
