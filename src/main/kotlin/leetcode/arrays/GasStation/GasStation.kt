/* gakshintala created on 11/17/19 */
package leetcode.arrays.GasStation

fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    val n = gas.size
    if (n == 1) {
        return if (gas[0] >= cost[0]) 0 else -1
    }
    var start = 0
    var end = 1
    var curGas = gas[start] - cost[start]
    
    while (start != end || curGas < 0) { // while loops like this, act as continuous if conditions.
        // We undo the tour with each position, instead of setting `start = end`, coz we need to identify if start is crossing over `0`.
        while (start != end && curGas < 0) { // Finally, end should be able to meet start without letting this loop run by always having curGas > 0
            curGas -= (gas[start] - cost[start]) // Undo the tour in this path, by removing gas and adding up the cost incurred.
            start = (start + 1) % n // `% n` is to get away with Cycle. Although start never crosses a cycle, if we use `start++`, it shall never be `0`
            if (start == 0) { // This indicates start has been chasing end, and they will never meet.
                return -1
            }
        }
        curGas += (gas[end] - cost[end]) // end drives the tour, add gas and minus distance cost.
        end = (end + 1) % n // `% n` is to get away with Cycle.
    }
    return end
}

fun main() {
    val gas = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    val cost = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    println(canCompleteCircuit(gas, cost))
}
