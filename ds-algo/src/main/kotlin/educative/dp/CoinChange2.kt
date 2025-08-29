package educative.dp

/* 24/7/25 17:38 */

/** [518. Coin Change II](https://leetcode.com/problems/coin-change-ii/) */
fun change(amount: Int, coins: IntArray): Int {
  val dp = IntArray(amount + 1)
  dp[0] = 1
  // * Building the entire sum introducing one coin type at a time
  for (coin in coins) {
    // ! Forward iteration as we allow the same coin to be used multiple times
    for (i in coin..amount) {
      dp[i] += dp[i - coin]
    }
  }
  return dp[amount]
}

fun main() {
  println(change(5, intArrayOf(1, 2, 5))) // 4
  println(change(3, intArrayOf(2))) // 0
  println(change(10, intArrayOf(10))) // 1
  println(change(0, intArrayOf(10))) // 1
}
