package leetcode.array

/** https://leetcode.com/problems/best-time-to-buy-and-sell-stock */
fun maxProfit(prices: IntArray): Int {
  var minBuyPrice = Int.MAX_VALUE
  var maxProfit = 0

  for (price in prices) {
    minBuyPrice = minOf(minBuyPrice, price)
    maxProfit = maxOf(maxProfit, price - minBuyPrice)
  }

  return maxProfit
}
