package educative.fusion

import java.util.PriorityQueue

/* 20 Jul 2025 18:16 */

fun employeeFreeTime(allEmployeeMeetings: List<List<Pair<Int, Int>>>): List<Pair<Int, Int>> {
  val minHeap = PriorityQueue(Comparator.comparingInt<Pair<Int, Int>> { it.first })
  // ! TODO 20 Jul 2025 gopala.akshintala: Memory optimize by loading only first element in each
  // list into Heap
  minHeap.addAll(allEmployeeMeetings.flatten())
  val nonOverlappingIntervals = mutableListOf<Pair<Int, Int>>()
  var prevIntervalEndTime = minHeap.peek().second
  while (minHeap.isNotEmpty()) {
    val curInterval = minHeap.poll()
    if (prevIntervalEndTime < curInterval.first) {
      nonOverlappingIntervals += (prevIntervalEndTime to curInterval.first)
    }
    prevIntervalEndTime = maxOf(prevIntervalEndTime, curInterval.second)
  }
  return nonOverlappingIntervals
}
