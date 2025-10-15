package educative.dp

/* 06 Aug 2025 18:45 */

fun solveRodCutting(lengths: IntArray, prices: IntArray, n: Int): Int {
  val dp = IntArray(n + 1)
  for ((length, price) in lengths.zip(prices)) {
    for (i in length..n) {
      dp[i] = maxOf(dp[i], price + dp[i - length])
    }
  }
  return dp[n]
}

fun main() {
  println(solveRodCutting(intArrayOf(1, 2, 3, 4, 5), intArrayOf(2, 6, 7, 10, 13), 5))
}
