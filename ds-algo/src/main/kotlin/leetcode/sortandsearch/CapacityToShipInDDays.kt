package leetcode.sortandsearch

/** https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ */
tailrec fun shipWithinDays(
  weights: IntArray,
  D: Int,
  minCap: Int = weights.maxOrNull()!!,
  maxCap: Int = weights.sum(),
): Int {
  if (minCap == maxCap) {
    return minCap
  }
  val curCap = (minCap + maxCap) / 2
  // ! 1 instead of 0 to compensate last iteration where we can't do `daysRequired++`
  var daysRequired = 1 
  weights.reduce { curSum, weight -> // loading weights to fill the cap
    if (curSum + weight > curCap) {
      daysRequired++
      weight
    } else {
      curSum + weight
    }
  }
  return when {
    daysRequired > D -> shipWithinDays(weights, D, curCap + 1, maxCap)
    else -> shipWithinDays(weights, D, minCap, curCap)
  }
}
