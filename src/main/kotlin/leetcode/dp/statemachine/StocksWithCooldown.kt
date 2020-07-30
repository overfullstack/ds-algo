package leetcode.dp

fun maxProfit(prices: IntArray): Int {
    var hold = Int.MIN_VALUE
    var sold = 0
    var rest = 0
    for (price in prices) {
        hold = maxOf(hold, rest - price) // You were holding or just held
        // sold can also come before rest calculation, as they are independent
        rest = maxOf(rest, sold) // You were in rest or just sold
        sold = hold + price // Only way to `sold` is from `hold`
    }
    return maxOf(sold, rest)
}
