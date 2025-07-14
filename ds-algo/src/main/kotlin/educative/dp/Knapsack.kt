package educative.dp

fun findMaxKnapsackProfit(capacity: Int, weights: IntArray, values: IntArray): Int {
  val table = IntArray(capacity + 1)
  table[0] = 0
  for (weight in weights) {
    for (j in weight..capacity) {
      table[j] = maxOf(table[j], values[j] + table[capacity - weights[j]])
    }
  }
  return table[capacity]
}
