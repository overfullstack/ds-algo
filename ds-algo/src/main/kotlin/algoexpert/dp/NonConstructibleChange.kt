package algoexpert.dp

fun minimumNonConstructableChange(coins: IntArray): Int {
  if (coins.isEmpty()) {
    return 1
  }
  val table = BooleanArray(coins.sum() + 1)
  table[0] = true
  var maxSumPossibleWithCurCoins = 0

  for (coin in coins) {
    maxSumPossibleWithCurCoins += coin
    table[coin] = true
    for (i in (coin + 1)..maxSumPossibleWithCurCoins) {
      table[i] = table[i] || table[i - coin]
    }
  }
  return table.withIndex().firstOrNull { (_, value) -> value == false }?.index
    ?: maxSumPossibleWithCurCoins + 1
}
