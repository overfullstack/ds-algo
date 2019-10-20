/* gakshintala created on 9/25/19 */
package leetcode

fun mergeIntervals(intervals: List<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
    val sortedIntervals = intervals.sortedBy { it.first }
    return sortedIntervals.drop(1).fold(mutableListOf(sortedIntervals.first())) { mergedIntervals, curInterval ->
        mergedIntervals.also {
            if (curInterval.first <= it.last().second) {
                it[it.lastIndex] =
                    Pair(it.last().first, maxOf(curInterval.second, it.last().second))
            } else {
                it.add(curInterval)
            }
        }
    }
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) return emptyArray()
    return mergeIntervals(intervals.map { Pair(it[0], it[1]) }).map { intArrayOf(it.first, it.second) }.toTypedArray()
}

fun main() {
    val intervals = listOf(Pair(1, 4), Pair(0, 4))
    mergeIntervals(intervals).forEach { println(it) }
}