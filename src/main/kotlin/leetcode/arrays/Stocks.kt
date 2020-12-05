package leetcode.arrays

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 */
fun maxProfit(prices: IntArray): Int {
    var min = Int.MAX_VALUE
    var maxProfit = 0

    for (price in prices) {
        min = minOf(min, price)
        maxProfit = maxOf(maxProfit, price - min)
    }

    return maxProfit
}
