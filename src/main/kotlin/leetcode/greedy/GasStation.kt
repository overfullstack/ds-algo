/* gakshintala created on 11/17/19 */
package leetcode.arrays.GasStation

/**
 * https://leetcode.com/problems/gas-station/
 */
fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
  var start = 0
  var totalGas = 0
  var totalCost = 0
  var curTank = 0
  for (i in gas.indices) { // * Imagine gas and tank racing.
    totalGas += gas[i]
    totalCost += cost[i]
    curTank += (gas[i] - cost[i])
    if (curTank < 0) { // ran out of gas at `i`
      start = i + 1
      curTank = 0
    }
  }
  // *  Ultimately, if `totalGas > totalCost`, a solution exists and there is no way cost can beat gas.
  // so to balance the equation `totalGas > totalCost`, the growth after `i` should compensate the deficit,
  // which can be carried over to complete loop from 0 to `i`
  return if (totalGas > totalCost) start else -1
}

fun canCompleteCircuit2(gas: IntArray, cost: IntArray): Int {
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
      // Undo the tour in this path, by removing gas and adding up the cost incurred. This is similar to sliding window.
      curGas -= (gas[start] - cost[start])
      // `% n` is to get away with Cycle. Although start never crosses a cycle, if we use `start++`, it shall never be `0`
      start = (start + 1) % n
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
