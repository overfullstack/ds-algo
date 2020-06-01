/* gakshintala created on 1/25/20 */
package leetcode.dp

fun jobScheduling(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray): Int =
    profitArr.zip(startTimeArr.zip(endTimeArr)).asSequence()
        .map { (profit, schedule) -> Triple(schedule.first, schedule.second, profit) }
        .sortedBy { it.second }
        .fold(mutableListOf(0 to 0)) { endToProfit, (startTime, endTime, curProfit) -> // We can use a `TreeMap` here to make things simple.
            val prevJobEndIndex = endToProfit.map { it.first }.getClosestJobEndIndex(startTime + 1) // previous job can end at `startTime`, so we use `startTime + 1`
            val prevJobProfit = endToProfit[prevJobEndIndex].second
            val profitIncludingCurJob = prevJobProfit + curProfit
            endToProfit.apply {
                if (profitIncludingCurJob > last().second) {
                    add(endTime to profitIncludingCurJob) // Always appended at last, in this context same as overriding previous result.
                }
            }
        }.last().second

/**
 * Find the index of the largest element < target in the given list.
 */
private tailrec fun List<Int>.getClosestJobEndIndex(targetEndTime: Int, left: Int = 0, right: Int = size - 1): Int {
    val mid = (left + right) / 2
    return when {
        left + 1 >= right -> if (this[right] < targetEndTime) right else left // This makes sure `left` never crosses `targetEnd`
        this[mid] < targetEndTime -> getClosestJobEndIndex(targetEndTime, mid, right)
        else -> getClosestJobEndIndex(targetEndTime, left, mid - 1)
    }
}
