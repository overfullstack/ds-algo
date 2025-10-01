package educative.dp

fun findMaxKnapsackProfitTopDown(
  capacity: Int,
  weights: IntArray,
  values: IntArray,
  idx: Int = 0,
  memo: Array<IntArray> = Array(weights.size) { IntArray(capacity + 1) { -1 } },
): Int =
  when {
    idx == weights.lastIndex + 1 -> 0
    memo[idx][capacity] != -1 -> memo[idx][capacity]
    else -> {
      memo[idx][capacity] =
        maxOf(
          findMaxKnapsackProfitTopDown(capacity, weights, values, idx + 1, memo),
          if (capacity - weights[idx] >= 0) {
            values[idx] +
              findMaxKnapsackProfitTopDown(capacity - weights[idx], weights, values, idx + 1, memo)
          } else Int.MIN_VALUE,
        )
      memo[idx][capacity]
    }
  }

fun findMaxKnapsackProfit(capacity: Int, weights: IntArray, values: IntArray): Int {
  val dp = IntArray(capacity + 1)
  // * Building by adding one weight at a time
  for ((weight, value) in weights.zip(values)) {
    // ! Reverse iteration, as we can include an item only once.
    // ! Notice how it's different from the Cake thief problem or Coin Change problem
    for (cap in capacity downTo weight) {
      dp[cap] = maxOf(dp[cap], value + dp[cap - weight])
    }
  }
  return dp[capacity]
}
