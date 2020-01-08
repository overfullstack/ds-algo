/* gakshintala created on 11/17/19 */
package leetcode.queue.GasStation

fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    val n = gas.size
    if (n == 1) {
        return if (gas[0] >= cost[0]) 0 else -1
    }
    var start = 0
    var end = 1
    var curGas = gas[start] - cost[start]
    
    while (start != end || curGas < 0) { // These while loops act as continuous if conditions.
        while (start != end && curGas < 0) { // Finally, end should be able to meet start without letting this loop run by always having curGas > 0
            curGas -= (gas[start] - cost[start]) // Undo the tour in this path, by removing gas and adding up the cost incurred.
            start = (start + 1) % n // `% n` is to get away with looping
            if (start == 0) { // This indicates start has been chasing end, and they will never meet.
                return -1
            }
        }
        curGas += (gas[end] - cost[end]) // end drives the tour, add gas and minus distance cost.
        end = (end + 1) % n
    }
    return end
}

fun main() {
    val gas = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    val cost = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    println(canCompleteCircuit(gas, cost))
}