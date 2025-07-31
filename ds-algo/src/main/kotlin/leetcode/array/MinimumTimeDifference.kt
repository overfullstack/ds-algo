package leetcode.array


/* 31 Jul 2025 16:21 */

/**
 * [539. Minimum Time Difference](https://leetcode.com/problems/minimum-time-difference)
 */
fun findMinDifference(timePoints: List<String>): Int {
  val sortedTimeInMins = timePoints.map {
    val times = it.split(":") 
    times.first().toInt() * 60 + times.last().toInt()
  }.sorted()

  // Functional approach: zip consecutive pairs and find minimum difference
  val minLinearTime = sortedTimeInMins.zipWithNext { a, b -> b - a }.min()
  
  // Handle circular case (last to first)
  val circularTime = 24 * 60 - sortedTimeInMins.last() + sortedTimeInMins.first()
  
  return minOf(minLinearTime, circularTime)
}
