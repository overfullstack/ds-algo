package leetcode.dp.statemachine

fun maxProfit(prices: IntArray, fee: Int): Int {
  var hold = Int.MIN_VALUE
  var sold = 0
  // * `hold` happens from `sold` state and vice-versa
  for (price in prices) {
    hold = maxOf(hold, sold - price)
    sold = maxOf(sold, hold + price - fee) // `fee` can be deducted either at hold or sold
  }
  return sold
}
