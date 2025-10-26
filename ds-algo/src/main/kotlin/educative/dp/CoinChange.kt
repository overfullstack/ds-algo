package educative.dp

/* 24/7/25 17:38 */

/** [322. Coin Change](https://leetcode.com/problems/coin-change/) */
fun coinChange(coins: IntArray, targetSum: Int): Int =
  // ! Forward iteration as we allow the same coin to be used multiple times
  when {
    targetSum == 0 -> 0
    targetSum < 0 || coins.isEmpty() -> -1
    else -> {
      val dp = IntArray(targetSum + 1) { Int.MAX_VALUE - 1 } // ! `-1` as we do `dp[i-coin]+1` below
      dp[0] = 0
      // * Building the entire sum introducing one coin type at a time
      for (coin in coins) {
        // ! `dp[i]` holds the fewest number of coins to make sum `i`
        // ! Forward iteration as we allow the same coin to be used multiple times
        for (sum in coin..targetSum) {
          // ! `minOf` exclude and include, for fewest number of coins
          dp[sum] = minOf(dp[sum], dp[sum - coin] + 1)
        }
      }
      if (dp[targetSum] == Int.MAX_VALUE - 1) -1 else dp[targetSum]
    }
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
  println(coinChange(intArrayOf(1, 2, 5), 11)) // 3
}
