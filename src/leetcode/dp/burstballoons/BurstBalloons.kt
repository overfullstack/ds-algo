/* gakshintala created on 9/22/19 */
package leetcode.dp.burstballoons

fun maxCoins(nums: IntArray): Int {
    val balloons = arrayOf(1) + nums.toTypedArray() + arrayOf(1) // this is leetcode problem specific
    val table = Array(balloons.size) { IntArray(balloons.size) }
    // The reason we start table at 1 is, when window is 2, first 3 represent calculation of 2 matrices. So table[1][2] indicates matrix multiplication of 0*1 and 1*2
    for (window in 2 until balloons.size) { // Window is for table
        for ((windowStart, windowEnd) in (window..balloons.lastIndex).withIndex()) {
            table[windowStart + 1][windowEnd] =
                (windowStart + 1 until windowEnd)
                    .map {
                        table[windowStart + 1][it]+table[it + 1][windowEnd]+balloons[windowStart] * balloons[it] * balloons[windowEnd]
                        // Multiplying A(p*q), B(q*r), C(r*s). Balloon: (pqrs). 
                        // balloons[windowStart] * balloons[partition] * balloons[windowEnd] - This is multiplying two matrices between (start-partition) (partition-end) 
                    }.max() ?: 0
        }
    }
    return table[1][balloons.lastIndex]
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    println(maxCoins(arr))
}