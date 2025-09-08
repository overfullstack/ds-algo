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

fun jobScheduling2(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray): Int =
  profitArr
    .zip(startTimeArr.zip(endTimeArr))
    .asSequence()
    .map { (profit, schedule) -> Triple(schedule.first, schedule.second, profit) }
    .sortedBy { it.second }
    // ! This is not a 01 Knapsack problem, as Intervals are fixed
    // ! So we just compare with the previous
    .fold(listOf(0 to 0)) { endTimeToMaxProfit, (curStart, curEnd, curProfit) ->
      val previousClosestEndingJobIndex =
        previousClosestEndingJobIndex(endTimeToMaxProfit, curStart)
      val prevJobProfit =
        if (previousClosestEndingJobIndex == -1) 0
        else endTimeToMaxProfit[previousClosestEndingJobIndex].second
      val profitIncludingCurJob = curProfit + prevJobProfit
      val profitExcludingCurJob = endTimeToMaxProfit.last().second
      val maxProfit = maxOf(profitIncludingCurJob, profitExcludingCurJob)
      endTimeToMaxProfit + (curEnd to maxProfit)
    }
    .last()
    .second

// * Rightmost where condition is true
fun previousClosestEndingJobIndex(
  endTimeToMaxProfit: List<Pair<Int, Int>>,
  curStartTime: Int,
): Int {
  var left = 0
  var right = endTimeToMaxProfit.lastIndex
  while (left <= right) {
    val mid = left + (right - left) / 2
    if (endTimeToMaxProfit[mid].first <= curStartTime) {
      left = mid + 1
    } else {
      right = mid - 1
    }
  }
  return right
}

fun main() {
  println(
    jobScheduling(
      intArrayOf(1, 2, 3, 4, 6),
      intArrayOf(3, 5, 10, 6, 9),
      intArrayOf(20, 20, 100, 70, 60),
    )
  )
  println(jobScheduling(intArrayOf(1, 1, 1), intArrayOf(2, 3, 4), intArrayOf(5, 6, 4)))
  println(
    jobScheduling2(
      intArrayOf(1, 2, 3, 4, 6),
      intArrayOf(3, 5, 10, 6, 9),
      intArrayOf(20, 20, 100, 70, 60),
    )
  )
  println(jobScheduling2(intArrayOf(1, 1, 1), intArrayOf(2, 3, 4), intArrayOf(5, 6, 4)))
}
