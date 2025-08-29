package leetcode.greedy

/**
 * [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) Find
 * the minimum number of intervals to be removed.
 */
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
  if (intervals.isEmpty()) {
    return 0
  }
  // ! Sort by **End** of interval
  val sortedIntervalsByEnd = intervals.map { it[0] to it[1] }.sortedBy { it.second }

  // Cannot use Int.MIN_VALUE as it can be a valid start interval
  var curIntervalEnd = sortedIntervalsByEnd[0].second
  val nonOverlapCount =
    1 + // ! For first interval
      sortedIntervalsByEnd
        .asSequence()
        .drop(1) // ! drop the first interval
        .filter { it.first >= curIntervalEnd }
        // * Update `curIntervalEnd` to find interval that starts after this
        .onEach { curIntervalEnd = it.second }
        .count()
  return intervals.size - nonOverlapCount // Problem needs min no.of intervals to be removed.
}
