package leetcode.dp.statemachine

/**
 * [309. Best Time to Buy and Sell Stock with
 * Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
 */
fun maxProfit(prices: IntArray): Int {
  var hold = Int.MIN_VALUE
  var sold = 0
  var rest = 0
  // ! 3 states
  for (price in prices) {
    hold = maxOf(hold, rest - price) // ! hold stock or buy from rest
    rest = maxOf(rest, sold) // ! Stay at rest or just sold
    sold = hold + price // ! Sell from hold
  }
  return maxOf(sold, rest) // Towards the end you either sell or already sold-and-continue-to-rest
}
