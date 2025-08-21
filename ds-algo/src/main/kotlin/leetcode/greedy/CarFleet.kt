package leetcode.greedy

/* 21 Aug 2025 16:55 */

/** [853. Car Fleet](https://leetcode.com/problems/car-fleet/) */
fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
  val map =
    position
      .zip(speed)
      .associate { it.first to (target - it.first).toDouble() / it.second }
      .toSortedMap(reverseOrder())
  var curTime = 0.0
  var fleetCount = 0
  for (time in map.values) {
    if (time > curTime) {
      curTime = time
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
