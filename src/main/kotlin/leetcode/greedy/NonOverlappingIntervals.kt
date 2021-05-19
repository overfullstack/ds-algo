package leetcode.greedy

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Find the minimum number of intervals to be removed.
 */
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    if (intervals.isEmpty()) {
        return 0
    }
    val sortedByEndIntervals = intervals.map { it[0] to it[1] }.sortedBy { it.second }

    var curIntervalEnd =
        sortedByEndIntervals[0].second // Cannot use Int.MIN_VALUE as it can be a valid start interval
    val nonOverlapCount = 1 + sortedByEndIntervals.asSequence().drop(1)
        .filter { it.first >= curIntervalEnd }
        .onEach {
            curIntervalEnd = it.second
        } // Update `curIntervalEnd` to find interval that starts after this
        .count()
    return intervals.size - nonOverlapCount // Problem needs min no.of intervals to be removed.
}
