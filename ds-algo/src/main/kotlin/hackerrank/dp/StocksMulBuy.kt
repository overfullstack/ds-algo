package hackerrank.dp

fun stockMax(prices: Array<Int>): Long {
  var profit = 0L
  var maxSoFar = Int.MIN_VALUE
  for (price in prices.reversed()) {
    maxSoFar = maxOf(maxSoFar, price)
    profit += (maxSoFar - price)
  }
  return profit
}
