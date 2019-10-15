package hackerrank

fun maxProfit(prices: IntArray): Int {
    var i = 0
    var maxProfit = 0
    val size = prices.size
    while (i < size) {
        // Get rid of slope-downs and only buy when it's rising.
        while (i < size - 1 && prices[i] >= prices[i + 1]) {
            i++
        }
        if (i == size - 1) {
            return maxProfit
        }
        val localMin = prices[i]
        while (i < size - 1 && prices[i] <= prices[i + 1]) {
            i++
        }

        maxProfit += (prices[i] - localMin)
    
    }
    return maxProfit
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        println(maxProfit(arr))
    }
}