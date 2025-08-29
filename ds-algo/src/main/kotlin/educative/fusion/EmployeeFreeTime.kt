package educative.fusion

import java.util.PriorityQueue

/* 20 Jul 2025 18:16 */

fun employeeFreeTime(allEmployeeMeetings: List<List<Pair<Int, Int>>>): List<Pair<Int, Int>> {
  val minHeap = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.first })
  // * Loading only first column instead of all intervals for Memory optimization
  minHeap.addAll(
    allEmployeeMeetings.withIndex().map { (row, empMeetings) ->
      Triple(empMeetings.first().first, row, 0)
    }
  )
  val gaps = mutableListOf<Pair<Int, Int>>()
  // ! Just a start value, we can't have `0` or `Int.MAX_VALUE` as we use for `<` and `maxOf` below
  var prevIntervalEndTime = minHeap.peek().first
  while (minHeap.isNotEmpty()) {
    val (curIntervalStartTime, row, col) = minHeap.poll() // ! Pick the earliest employee meeting
    if (prevIntervalEndTime < curIntervalStartTime) {
      gaps += (prevIntervalEndTime to curIntervalStartTime)
    }
    prevIntervalEndTime = maxOf(prevIntervalEndTime, allEmployeeMeetings[row][col].second)
    if (col + 1 <= allEmployeeMeetings[row].lastIndex) { // Add employee's next meeting
      minHeap.add(Triple(allEmployeeMeetings[row][col + 1].first, row, col + 1))
    }
  }
  return gaps
}
