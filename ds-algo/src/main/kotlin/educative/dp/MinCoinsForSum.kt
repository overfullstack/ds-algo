package educative.dp

/* 24/7/25 17:38 */

/** [Coin Change](https://leetcode.com/problems/coin-change/)* */
fun minCoinsForSum(coins: IntArray, sum: Int): Int {
  if (sum == 0) return 0
  if (sum < 0 || coins.isEmpty()) return -1

  val dp = IntArray(sum + 1) { Int.MAX_VALUE - 999 }
  dp[0] = 0
  // * Building the entire sum introducing one coin type at a time
  for (coin in coins) {
    // ! Forward iteration as we allow the same coin to be used multiple times
    for (i in coin..sum) {
      dp[i] = minOf(dp[i], dp[i - coin] + 1)
    }
  }
  return if (dp[sum] == Int.MAX_VALUE - 999) -1 else dp[sum]
}

fun minCoinsForSum2(coins: IntArray, sum: Int): Int {
  if (sum == 0) return 0
  if (sum < 0 || coins.isEmpty()) return -1

  val table = IntArray(sum + 1) { Int.MAX_VALUE }
  table[0] = 0
  for (i in 1..sum) {
    for (coin in coins) {
      if (coin <= i && table[i - coin] != Int.MAX_VALUE) {
        table[i] = minOf(table[i], table[i - coin] + 1)
      }
    }
  }
  return if (table[sum] == Int.MAX_VALUE) -1 else table[sum]
}

fun main() {
  println(minCoinsForSum(intArrayOf(2, 3, 4, 6, 8), 23))
}
