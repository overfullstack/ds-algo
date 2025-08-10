/* gakshintala created on 1/25/20 */
package leetcode.dp

import java.util.TreeMap

/**
 * [1235. Maximum Profit in Job
 * Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/)
 */
fun jobScheduling(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray): Int =
  profitArr
    .zip(startTimeArr.zip(endTimeArr))
    .asSequence()
    .map { (profit, schedule) -> Triple(schedule.first, schedule.second, profit) }
    .sortedBy { it.second } // ! sort by endTime
    .fold(TreeMap(mapOf(0 to 0))) { endTimeToMaxProfit, (startTime, endTime, curProfit) ->
      // ! `startTime` coz prevJob end and nextJob start can happen at the same time
      val prevJobProfit = endTimeToMaxProfit.floorEntry(startTime)?.value ?: 0
      val profitIncludingCurJob = prevJobProfit + curProfit
      val profitExcludingCurJob = endTimeToMaxProfit.lastEntry().value
      endTimeToMaxProfit[endTime] = maxOf(profitIncludingCurJob, profitExcludingCurJob)
      endTimeToMaxProfit
    }
    .lastEntry()
    .value

fun main() {
  println(
    jobScheduling(
      intArrayOf(1, 2, 3, 4, 6),
      intArrayOf(3, 5, 10, 6, 9),
      intArrayOf(20, 20, 100, 70, 60),
    )
  )
  println(jobScheduling(intArrayOf(1, 1, 1), intArrayOf(2, 3, 4), intArrayOf(5, 6, 4)))
}
