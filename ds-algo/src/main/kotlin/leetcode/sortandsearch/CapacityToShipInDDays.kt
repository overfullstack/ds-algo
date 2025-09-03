package leetcode.sortandsearch

/**
 * [1011. Capacity To Ship Packages Within D
 * Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)
 */
tailrec fun shipWithinDays(
  weights: IntArray,
  d: Int,
  minCap: Int = weights.maxOrNull()!!,
  maxCap: Int = weights.sum(),
): Int {
  if (minCap == maxCap) {
    return maxCap
  }
  val curCap = (minCap + maxCap) / 2
  // ! We cannot use `(weights.sum() / curCap) + 1` as they need to be shipped in-order
  // ! curCap is always >= maxWeight as lowerBound is initialized to maxWeight
  val daysRequired = weights
      .fold(0 to 0) { (daysCount, accWeight), weight ->
        val curWeight = accWeight + weight
        when {
            curWeight > curCap -> (daysCount + 1) to weight
            else -> daysCount to curWeight
        }
      }
      .first + 1 // ! `+1` for the reminder
  // ! Minimum Capacity = Leftmost
  // ! Unlike classic Leftmost Binary search condition for Leftmost `this[mid] >= valueToSearch`
  // ! this condition moves in reverse direction 
  // ! as `capacity` and `daysRequired` are inversely proportional
  // ! so the condition is reverse `daysRequired <= d`
  return when {
    daysRequired <= d -> shipWithinDays(weights, d, minCap, curCap)
    else -> shipWithinDays(weights, d, curCap + 1, maxCap)
  }
}
