package educative.dp

fun countingBits(n: Int): IntArray {
  if (n <= 2) {
    return if (n == 1) intArrayOf(0, 1) else intArrayOf(0)
  }
  val results = IntArray(n + 1)
  results[0] = 0
  results[1] = 1
  for (i in 2..n) {
    results[i] = results[i/2] + i % 2
  }
  return results
}

fun main() {
  countingBits(10).forEach { print("$it ") }
}
