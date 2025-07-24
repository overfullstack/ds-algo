package educative.dp

/* 24/7/25 17:00 */

fun distinctWaysToClimb(n: Int): Int {
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
  println(distinctWaysToClimb(30)) // 1346269
}
