/* gakshintala created on 9/25/19 */
package leetcode

fun mergeIntervals(intervals: List<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
    val mergedIntervals = mutableListOf<Pair<Int, Int>>()
    val sortedIntervals = intervals.sortedBy { it.first }
    mergedIntervals.add(sortedIntervals[0])
    for (curInterval in sortedIntervals.drop(1)) {
        if (curInterval.first <= mergedIntervals.last().second) {
            mergedIntervals[mergedIntervals.lastIndex] =
                Pair(mergedIntervals.last().first, maxOf(curInterval.second, mergedIntervals.last().second))
        } else {
            mergedIntervals.add(curInterval)
        }
    }
    return mergedIntervals
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) return emptyArray()
    return mergeIntervals(intervals.map { Pair(it[0], it[1]) }).map { intArrayOf(it.first, it.second) }.toTypedArray()
}

fun main() {
    val intervals = listOf(Pair(1, 4), Pair(0, 4))
    mergeIntervals(intervals).forEach { println(it) }
}