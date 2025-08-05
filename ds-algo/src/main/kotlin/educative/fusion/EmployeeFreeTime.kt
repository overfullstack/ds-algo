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
  var prevIntervalEndTime = minHeap.peek().first
  while (minHeap.isNotEmpty()) {
    val (curIntervalStartTime, row, col) = minHeap.poll()
    if (prevIntervalEndTime < curIntervalStartTime) {
      gaps += (prevIntervalEndTime to curIntervalStartTime)
    }
    prevIntervalEndTime = maxOf(prevIntervalEndTime, allEmployeeMeetings[row][col].second)
    if (col + 1 <= allEmployeeMeetings[row].lastIndex) {
      minHeap.add(Triple(allEmployeeMeetings[row][col + 1].first, row, col + 1))
    }
  }
  return gaps
}
