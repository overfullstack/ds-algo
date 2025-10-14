package leetcode.dp

/**
 * https://leetcode.com/discuss/post/726978/number-of-ways-to-have-breakfast-dp-micr-aizf/ Bread -
 * daily, Pizza - alternate, Burger - Every 2 days
 */
fun waysToHaveBreakfast(n: Int): Int {
  val table = Array(n + 1) { IntArray(3) }
  table[1][0] = 1
  table[1][1] = 1
  table[1][2] = 1
  for (i in 2..n) {
    table[i][0] = table[i - 1][0] + table[i - 1][1] + table[i - 1][2]
    table[i][1] = table[i - 1][0] + table[i - 1][2]
    // ! Negate burger days carried from the last 2 days
    table[i][2] = table[i - 1][0] + table[i - 1][1] - 2 * table[i - 2][2]
  }
  return table[n][0] + table[n][1] + table[n][2]
}

fun main() {
  println(waysToHaveBreakfast(2)) // 7
  println(waysToHaveBreakfast(3)) // 15
}
