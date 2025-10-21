package educative.dp

/* 24/7/25 17:00 */

/** [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) */
fun distinctWaysToClimbTopDown(n: Int, memo: IntArray = IntArray(n + 1)): Int =
  when {
    n <= 2 -> n
    memo[n] != 0 -> memo[n]
    else ->
      (distinctWaysToClimbTopDown(n - 1, memo) + distinctWaysToClimbTopDown(n - 2, memo)).also {
        memo[n] = it
      }
  }

fun distinctWaysToClimbBottomUp(n: Int): Int {
  if (n <= 2) return n
  var first = 1
  var second = 2
  repeat(n - 2) {
    val next = first + second
    first = second
    second = next
  }
  return second
}

fun main() {
  println(distinctWaysToClimbBottomUp(30)) // 1346269
}
