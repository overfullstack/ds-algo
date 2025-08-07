package educative.dp

fun findMaxKnapsackProfit(capacity: Int, weights: IntArray, values: IntArray): Int {
  val dp = IntArray(capacity + 1)
  // * Building by adding one weight at a time
  for ((weight, value) in weights.zip(values)) {
    // ! Reverse iteration, as we can include an item only once. Notice how it's different from
    // the Cake thief problem or Coin Change problem
    for (cap in capacity downTo weight) {
      dp[cap] = maxOf(dp[cap], value + dp[cap - weight])
    }
  }
  return dp[capacity]
}
