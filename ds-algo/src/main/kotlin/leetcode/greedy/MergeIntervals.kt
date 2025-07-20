/* gakshintala created on 9/25/19 */
package leetcode.greedy

fun mergeIntervals(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
  val sortedByStartIntervals = intervals.sortedBy { it.first }
  return sortedByStartIntervals.drop(1).fold(listOf(sortedByStartIntervals.first())) {
    mergedIntervals,
    curInterval ->
    val prevInterval = mergedIntervals.last()
    when {
      prevInterval.second >= curInterval.first ->
        mergedIntervals.dropLast(1) +
          (prevInterval.first to maxOf(prevInterval.second, curInterval.second))
      else -> mergedIntervals + curInterval
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
    "[[1,3],[2,6],[8,10],[15,18]]" // readln()
      .drop(2)
      .dropLast(2)
      .split("],[")
      .map { row -> row.split(",").map { it.trim().toInt() }.toIntArray() }
      .toTypedArray()
  merge(intervals).forEach { println(it.toList()) }
}
