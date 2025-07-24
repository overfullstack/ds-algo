/* gakshintala created on 1/25/20 */
package leetcode.dp

/** https://leetcode.com/problems/maximum-profit-in-job-scheduling/ */
fun jobScheduling(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray): Int =
  profitArr
    .zip(startTimeArr.zip(endTimeArr))
    .asSequence()
    .map { (profit, schedule) -> Triple(schedule.first, schedule.second, profit) }
    .sortedBy { it.second }
    .fold(listOf(0 to 0)) { endTimeToMaxProfit, (startTime, endTime, curProfit) ->
      // We can use a `TreeMap` here to simplify things.
      val prevJobEndIndex =
        endTimeToMaxProfit
          .map { it.first }
          // ! `start + 1` coz prevJob end and nextJob start can happen at the same time
          .getClosestJobEndIndex(startTime + 1)
      val prevJobProfit =
        if (prevJobEndIndex == -1) 0 else endTimeToMaxProfit[prevJobEndIndex].second
      val profitIncludingCurJob = prevJobProfit + curProfit
      val profitExcludingCurJob = endTimeToMaxProfit.last().second
      endTimeToMaxProfit + (endTime to maxOf(profitIncludingCurJob, profitExcludingCurJob))
    }
    .last()
    .second // profit in the end

/** Find the rightmost index of the largest element < target in the given list. */
private tailrec fun List<Int>.getClosestJobEndIndex(
  targetEndTime: Int,
  left: Int = 0,
  right: Int = lastIndex,
): Int =
  when {
    left == right ->
      when {
        this[left] < targetEndTime -> left
        else -> left - 1 // ! This either gives a valid index or -1
      }
    else -> {
      val mid = (left + right + 1) / 2 // `+1` coz we are right-biased when left + 1 = right
      when {
        // `mid` instead of `mid + 1`, mid can also be a valid candidate
        this[mid] < targetEndTime -> getClosestJobEndIndex(targetEndTime, mid, right)
        else -> getClosestJobEndIndex(targetEndTime, left, mid - 1)
      }
    }
  }
