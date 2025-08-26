package educative.greedy

import java.util.PriorityQueue

/**
 * [871. Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops)
 */
fun minimumRefuelingStations(
  targetDistance: Int,
  startFuel: Int,
  stations: List<Pair<Int, Int>>,
): Int {
  // ! We don't need minHeap as the distance is sorted
  val maxHeapForFuel = PriorityQueue(reverseOrder<Int>())
  var reachableDistance = startFuel
  var i = 0
  var stops = 0
  while (reachableDistance < targetDistance) {
    while (i <= stations.lastIndex && stations[i].first <= reachableDistance) {
      maxHeapForFuel.add(stations[i].second)
      i++
    }
    if (maxHeapForFuel.isEmpty()) {
      return -1
    } 
    reachableDistance += maxHeapForFuel.poll()
    stops++
  }
  return stops
}
