package leetcode.greedy

/* 21 Aug 2025 16:55 */

/** [853. Car Fleet](https://leetcode.com/problems/car-fleet/) */
fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
  val carsSortedByPosCloserToTarget =
    position
      .zip(speed)
      .associate { it.first to (target - it.first).toDouble() / it.second }
      .toSortedMap(reverseOrder())
  var aheadCarTime = 0.0
  var fleetCount = 0
  for (time in carsSortedByPosCloserToTarget.values) {
    // ! If a car behind takes more time to reach target
    // ! it can never catch up with the car in front of it. So, it forms a new fleet.
    if (time > aheadCarTime) {
      aheadCarTime = time
      fleetCount++
    }
  }
  return fleetCount
}

fun main() {
  println(carFleet(12, intArrayOf(10, 8, 0, 5, 3), intArrayOf(2, 4, 1, 1, 3))) // 3
  println(carFleet(10, intArrayOf(3), intArrayOf(3))) // 1
  println(carFleet(100, intArrayOf(0, 2, 4), intArrayOf(4, 2, 1))) // 1
}
