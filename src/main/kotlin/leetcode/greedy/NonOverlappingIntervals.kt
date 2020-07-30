package leetcode.greedy

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 */
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    if (intervals.isEmpty()) {
        return 0
    }
    val sortedIntervals = intervals.map { it[0] to it[1] }.sortedBy { it.second }

    var end = sortedIntervals[0].second // Cannot use Int.MIN_VALUE as it can be a valid start interval
    val nonOverlapCount = 1 + sortedIntervals.asSequence().drop(1)
        .filter { it.first >= end }
        .onEach {
            end = it.second
        } // You will keep the end of a window, untill you find a window which starts after this end.
        .count()
    return intervals.size - nonOverlapCount
}
