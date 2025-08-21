package educative.greedy

import java.util.PriorityQueue

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
    when {
      maxHeapForFuel.isNotEmpty() -> {
        reachableDistance += maxHeapForFuel.poll()
        stops++
      }
      else -> return -1
    }
  }
  return stops
}
