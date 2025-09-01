package leetcode.greedy

/**
 * [1094. Car Pooling](https://leetcode.com/problems/car-pooling/) trips: arr[No.of Passengers,
 * Start, End] Is it possible to pick up and drop off all passengers for all the given trips
 */
fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
  val tripsSortedByOrigin = trips.sortedBy { it[1] }
  // * This stores the net on-boarding at every point.
  val map = sortedMapOf<Int, Int>() // ! This is a SortedMap
  for ((passengersCount, start, end) in tripsSortedByOrigin) {
    map.merge(start, passengersCount, Int::plus)
    map.merge(end, passengersCount, Int::minus)
  }
  return map.values.asSequence().runningReduce(Int::plus).all { it <= capacity }
}

fun carPooling2(trips: Array<IntArray>, capacity: Int): Boolean {
  val (originInfo, destInfo) = trips.map { Pair(it[0], it[1]) to Pair(it[0], it[2]) }.unzip()
  val sortedByOrigin = originInfo.sortedBy { it.second }
  val sortedByDest = destInfo.sortedBy { it.second }

  var originIndex = 0
  var destIndex = 0
  var pickUps = 0
  var maxPickUps = 0
  // * Loop through both the array like merge sort
  while (originIndex <= sortedByOrigin.lastIndex && destIndex <= sortedByDest.lastIndex) {
    if (sortedByOrigin[originIndex].second < sortedByDest[destIndex].second) {
      pickUps += sortedByOrigin[originIndex].first
      maxPickUps = maxOf(maxPickUps, pickUps)
      if (maxPickUps > capacity) {
        return false
      }
      originIndex++
    } else {
      pickUps -= sortedByDest[destIndex].first
      destIndex++
    }
  }
  return true
}
