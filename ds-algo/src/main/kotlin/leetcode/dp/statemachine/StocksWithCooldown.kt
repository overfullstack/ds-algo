package leetcode.dp.statemachine

/**
 * [309. Best Time to Buy and Sell Stock with
 * Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
 */
fun maxProfit(prices: IntArray): Int {
  var hold = Int.MIN_VALUE
  var sold = 0
  var rest = 0
  for (price in prices) {
    hold = maxOf(hold, rest - price) // You were holding or just held (or bought)
    // sold can also come before rest calculation, as they are independent
    rest = maxOf(rest, sold) // You were in rest or just sold
    sold = hold + price // Only way to `sold` is from `hold`
  }
  return maxOf(sold, rest) // Towards the end you either sell or already sold-and-continue-to-rest
}
