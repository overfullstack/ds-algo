package educative.fusion

/* 20 Jul 2025 12:28 */

fun insertInterval(
  sortedIntervals: List<Pair<Int, Int>>,
  newInterval: Pair<Int, Int>,
): List<Pair<Int, Int>> {
  val intervalsThatStartEarly = sortedIntervals.filter { it.first < newInterval.first }
  val mergedIntervals =
    intervalsThatStartEarly.lastOrNull()?.let {
      when {
        it.second >= newInterval.first ->
          intervalsThatStartEarly.dropLast(1) + (it.first to maxOf(it.second, newInterval.second))
        else -> intervalsThatStartEarly + newInterval
      }
    } ?: listOf(newInterval)
  return sortedIntervals.drop(intervalsThatStartEarly.size).fold(mergedIntervals) {
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

fun main() {
  insertInterval(listOf(1 to 6, 8 to 9, 10 to 15, 16 to 18), 9 to 10)
}
