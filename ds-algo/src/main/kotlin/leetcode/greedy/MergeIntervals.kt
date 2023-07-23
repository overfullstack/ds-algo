/* gakshintala created on 9/25/19 */
package leetcode.greedy

fun mergeIntervals(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
  val sortedIntervals = intervals.sortedBy { it.first }
  val mergedIntervals = mutableListOf<Pair<Int, Int>>()

  return mergedIntervals +
    sortedIntervals.reduce { mergedInterval, curInterval
      -> // aggregate (to previous) or replace (with current)
      if (curInterval.first <= mergedInterval.second) {
        mergedInterval.first to
          maxOf(curInterval.second, mergedInterval.second) // aggregate an interval.
      } else {
        mergedIntervals += mergedInterval
        curInterval // repalce prev aggergate with current interval.
      }
    }
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
  if (intervals.isEmpty()) return emptyArray()
  return mergeIntervals(intervals.map { Pair(it[0], it[1]) })
    .map { intArrayOf(it.first, it.second) }
    .toTypedArray()
}

fun main() {
  val intervals =
    readln()
      .drop(2)
      .dropLast(2)
      .split("],[")
      .map { row -> row.split(",").map { it.trim().toInt() }.toIntArray() }
      .toTypedArray()
  merge(intervals).forEach { println(it.toList()) }
}
