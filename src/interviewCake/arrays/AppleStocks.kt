package interviewCake.arrays

fun appleStocksMaxProfit(prices: IntArray): Int {
    if(prices.isEmpty()) {
        return 0
    }
    var max = prices.last()
    var maxProfit = 0
    for (i in prices.lastIndex - 1 downTo 0) {
        if (prices[i] > max) {
            max = prices[i]
        } else {
            maxProfit = maxOf(maxProfit, max - prices[i])
        }
    }

    return maxProfit
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        println(appleStocksMaxProfit(arr))
    }
}

