/* gakshintala created on 11/17/19 */
package leetcode.greedy

/** https://leetcode.com/problems/gas-station/ */
fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {

  var start = 0
  var totalGas = 0
  var totalCost = 0
  var curTank = 0

  for (i in gas.indices) {
    // Track total resources to check if solution is possible
    totalGas += gas[i]
    totalCost += cost[i]

    // Calculate net fuel gain/loss at current station
    // (gas[i] - cost[i]) = fuel gained - fuel needed for next station
    curTank += (gas[i] - cost[i])
    // If tank becomes negative, we can't reach the next station
    // from our current starting point
    if (curTank < 0) {
      // Greedy insight: If we can't reach station `i+1` from current start,
      // then we can't reach station `i+1` from ANY station between current start and station i
      start = i + 1 // Try starting from the next station
      curTank = 0 // Reset tank to empty (we're starting fresh)
    }
  }

  // Final check: If total gas >= total cost, a solution exists
  // The 'start' variable now points to the valid starting station
  // If total gas < total cost, it's impossible to complete the circuit
  return if (totalGas >= totalCost) start else -1
}

fun main() {
  val gas = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val cost = readln().split(",").map { it.trim().toInt() }.toIntArray()
  println(canCompleteCircuit(gas, cost))
}
