package educative.greedy

import java.util.PriorityQueue

fun minimumRefuelingStations(
  targetDistance: Int,
  startFuel: Int,
  stations: List<Pair<Int, Int>>,
): Int {
  val maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())
  var reachableDistance = startFuel
  var i = 0
  var stops = 0
  while (reachableDistance < targetDistance) {
    while (i <= stations.lastIndex && stations[i].first <= reachableDistance) {
      maxHeap.add(stations[i].second)
      i++
    }
    when {
      maxHeap.isNotEmpty() -> {
        reachableDistance += maxHeap.poll()
        stops++
      }
      else -> return -1
    }
  }
  return stops
}
