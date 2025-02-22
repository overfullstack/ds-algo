package leetcode.greedy

/** https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/ */
fun findMinArrowShots(points: Array<IntArray>): Int {
  if (points.isEmpty()) {
    return 0
  }
  val sortedPoints = points.map { it[0] to it[1] }.sortedBy { it.second }
  var end = sortedPoints[0].second
  val nonOverlapCount =
    sortedPoints
      .asSequence()
      .drop(1)
      .filter {
        it.first > end
      } // > instead of >=, in this problem contact windows are treated as overlap
      .onEach { end = it.second }
      .count()
  return 1 + nonOverlapCount
}
