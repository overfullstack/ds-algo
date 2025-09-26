package educative.fusion

import java.util.PriorityQueue
import utils.toPair

/* 20 Jul 2025 18:16 */

/** [850 · Employee Free Time](https://www.lintcode.com/problem/850/) */
fun employeeFreeTime(schedule: Array<IntArray>): List<Interval?> =
  employeeFreeTime(schedule.map { it.toList().chunked(2).map { it.toPair() } }).map {
    Interval(it.first, it.second)
  }

fun employeeFreeTime(allEmployeeMeetings: List<List<Pair<Int, Int>>>): List<Pair<Int, Int>> {
  val minHeap =
    PriorityQueue(compareBy<Pair<Int, Int>> { allEmployeeMeetings[it.first][it.second].first })
  // ! Add row, col into heap, so we can progress on each row to next col
  // ! Loading only first column instead of all intervals for Memory optimization
  minHeap.addAll(allEmployeeMeetings.withIndex().map { (row, _) -> row to 0 })
  val gaps = mutableListOf<Pair<Int, Int>>()
  val (row, col) = minHeap.peek()
  // ! Init with prevIntervalEndTime, it will surpass the first interval
  var prevIntervalEndTime = allEmployeeMeetings[row][col].second
  while (minHeap.isNotEmpty()) {
    val (row, col) = minHeap.poll() // ! Pick the earliest employee meeting
    val curIntervalStartTime = allEmployeeMeetings[row][col].first
    if (prevIntervalEndTime < curIntervalStartTime) {
      gaps += (prevIntervalEndTime to curIntervalStartTime)
    }
    prevIntervalEndTime = maxOf(prevIntervalEndTime, allEmployeeMeetings[row][col].second)
    if (col + 1 <= allEmployeeMeetings[row].lastIndex) { // ! Add employee's next meeting
      minHeap.add(row to col + 1)
    }
  }
  return gaps
}

data class Interval(val start: Int, val end: Int)

fun main() {
  val schedule = arrayOf(intArrayOf(1, 2, 5, 6), intArrayOf(1, 3), intArrayOf(4, 10))
  employeeFreeTime(schedule).forEach { println(it) }
}
