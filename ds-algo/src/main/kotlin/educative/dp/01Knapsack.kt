package educative.dp

fun findMaxKnapsackProfit(capacity: Int, weights: IntArray, values: IntArray): Int {
  val table = IntArray(capacity + 1) { 0 }
  for ((weight, value) in weights.zip(values)) {
    // ! Reverse iteration, as we can include an item only once. Notice how it's different from
    // the Cake thief problem or Coin Change problem
    for (cap in capacity downTo weight) {
      table[cap] = maxOf(table[cap], value + table[cap - weight])
    }
  }
  return table[capacity]
}
