/* gakshintala created on 1/25/20 */
package leetcode.dp

data class Job(val startTime: Int, val endTime: Int, val profit: Int) : Comparable<Job> {
    override fun compareTo(other: Job) = Comparator.comparingInt<Job> { it.profit }.compare(this, other)
}

fun jobScheduling(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray) =
    profitArr.zip(startTimeArr.zip(endTimeArr)).asSequence()
        .map { (profit, schedule) -> Job(schedule.first, schedule.second, profit) }
        .sortedBy { it.endTime }
        .fold(mutableListOf(0 to 0)) { endToProfit, curJob ->
            val prevJobEndIndex = endToProfit.map { it.first }.getClosestJobEndIndex(curJob.startTime + 1)
            val prevJobProfit = endToProfit[prevJobEndIndex].second
            val profitIncludingCurJob = prevJobProfit + curJob.profit
            endToProfit.also {
                if (profitIncludingCurJob > endToProfit.last().second) {
                    it.add(curJob.endTime to profitIncludingCurJob)
                }
            }
        }.last().second

private tailrec fun List<Int>.getClosestJobEndIndex(targetEndTime: Int, left: Int = 0, right: Int = size - 1): Int {
    val mid = (left + right) / 2
    return when {
        left + 1 >= right -> if (this[right] < targetEndTime) right else left
        this[mid] < targetEndTime -> getClosestJobEndIndex(targetEndTime, mid, right)
        else -> getClosestJobEndIndex(targetEndTime, left, mid - 1)
    }
}

