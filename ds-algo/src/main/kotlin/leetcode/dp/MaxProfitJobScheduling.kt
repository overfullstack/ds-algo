/* gakshintala created on 1/25/20 */
package leetcode.dp

/** https://leetcode.com/problems/maximum-profit-in-job-scheduling/ ! Unsubmitted */
fun jobScheduling(startTimeArr: IntArray, endTimeArr: IntArray, profitArr: IntArray): Int =
  profitArr
    .zip(startTimeArr.zip(endTimeArr))
    .asSequence()
    .map { (profit, schedule) -> Triple(schedule.first, schedule.second, profit) }
    .sortedBy { it.second }
    .fold(mutableListOf(0 to 0)) { endToMaxProfit, (startTime, endTime, curProfit) ->
      // We can use a `TreeMap` here to simplify things.
      val prevJobEndIndex =
        endToMaxProfit
          .map { it.first }
          .getClosestJobEndIndex(
            startTime + 1
          ) // previous job can end at `startTime`, so we use `startTime + 1`
      val prevJobProfit = endToMaxProfit[prevJobEndIndex].second
      val profitIncludingCurJob = prevJobProfit + curProfit
      endToMaxProfit.apply {
        if (profitIncludingCurJob > last().second) {
          add(
            endTime to profitIncludingCurJob
          ) // Always appended at last, in this context the same as overriding previous result.
        }
      }
    }
    .last()
    .second // profit in the end

/** Find the index of the largest element < target in the given list. */
private tailrec fun List<Int>.getClosestJobEndIndex(
  targetEndTime: Int,
  left: Int = 0,
  right: Int = size - 1
): Int {
  val mid = (left + right) / 2
  return when {
    left + 1 >= right ->
      if (this[right] < targetEndTime) right
      else left // This makes sure `left` never cross `targetEnd`
    this[mid] < targetEndTime -> getClosestJobEndIndex(targetEndTime, mid, right)
    else -> getClosestJobEndIndex(targetEndTime, left, mid - 1)
  }
}
