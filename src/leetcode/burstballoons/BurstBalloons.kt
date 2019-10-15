/* gakshintala created on 9/22/19 */
package leetcode.burstballoons

fun maxCoins(nums: IntArray): Int {
    val balloons = arrayOf(1) + nums.toTypedArray() + arrayOf(1)
    val table = Array(balloons.size) { IntArray(balloons.size) }
    // The reason we start table at 1 is, when window is 2, first 3 represent calculation of 2 matrices. So table[1][2] indicates matric multiplication of 0*1 and 1*2
    for (window in 2 until balloons.size) {
        for ((start, end) in (window..balloons.lastIndex).withIndex()) {
            table[start + 1][end] = (start + 1 until end).fold(Int.MIN_VALUE) { res, partition ->
                maxOf(
                    res,
                    table[start + 1][partition] + table[partition + 1][end] + balloons[start] * balloons[partition] * balloons[end]
                )
            }
        }
    }
    return table[1][balloons.lastIndex]
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    println(maxCoins(arr))
}