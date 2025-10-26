package educative.dp

/* 24/7/25 17:38 */

/** [518. Coin Change II](https://leetcode.com/problems/coin-change-ii/) */
fun change(targetAmount: Int, coins: IntArray): Int {
  val dp = IntArray(targetAmount + 1)
  dp[0] = 1
  // * Building the entire sum introducing one coin type at a time
  for (coin in coins) {
    // ! No.of ways => Borrow from previous indices and add-up
    // ! Forward iteration as we allow the same coin to be used multiple times
    for (amount in coin..targetAmount) {
      dp[amount] += dp[amount - coin]
    }
  }
  return dp[targetAmount]
}

fun main() {
  println(change(5, intArrayOf(1, 2, 5))) // 4
  println(change(3, intArrayOf(2))) // 0
  println(change(10, intArrayOf(10))) // 1
  println(change(0, intArrayOf(10))) // 1
}
