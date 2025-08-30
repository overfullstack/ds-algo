package leetcode.greedy

/**
 * [452. Minimum Number of Arrows to Burst
 * Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
 */
fun findMinArrowShots(points: Array<IntArray>): Int {
  if (points.isEmpty()) {
    return 0
  }
  // ! Sort by end time, as we shoot at end point of balloon.
  // ! All balloons sharing this end point in their interval are burst by the same arrow
  val sortedBalloonsByEnd = points.map { it[0] to it[1] }.sortedBy { it.second }
  var end = sortedBalloonsByEnd[0].second
  val nonOverlapCount =
    1 + // ! +1 for the first balloon
      sortedBalloonsByEnd
        .asSequence()
        .drop(1)
        .filter { it.first > end } // * 1 arrow for all balloon starting before this end point
        .onEach { end = it.second } // ! `it.second` instead of `maxOf(it.second, end)`
        .count()
  return nonOverlapCount
}
