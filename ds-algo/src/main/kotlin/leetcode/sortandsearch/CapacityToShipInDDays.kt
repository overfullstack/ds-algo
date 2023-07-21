package leetcode.sortandsearch

/** https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ */
fun shipWithinDays(
  weights: IntArray,
  D: Int,
  minCap: Int = weights.maxOrNull()!!,
  maxCap: Int = weights.sum()
): Int {
  // * Recursion doesn't stop when `daysRequired=D`. It stops when no more window left to search.
  if (minCap == maxCap) {
    return minCap
  }
  var curCap = (minCap + maxCap) / 2
  var daysRequired =
    1 // ! 1 instead of 0 to compensate last iteration where we can't do `daysRequred++`
  weights.reduce { curSum, weight -> // loading weights to fill the cap
    if (curSum + weight > curCap) {
      daysRequired++
      weight
    } else {
      curSum + weight
    }
  }
  // * Recursion doesn't stop when `daysRequired=D`. It stops when no more window left to search.
  return when {
    daysRequired > D -> shipWithinDays(weights, D, curCap + 1, maxCap)
    // ! Observe no curCap - 1 for right, as it is curCap or less
    // * If daysRequired is equal or less, we squeeze till we get the min Cap
    else -> shipWithinDays(weights, D, minCap, curCap)
  }
}
