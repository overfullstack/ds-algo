package educative.fusion

import utils.toIntArray
import utils.toPair

/* 20 Jul 2025 16:12 */

/**
 * [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)
 */
fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> =
  intervalListIntersections(firstList.map { it.toPair() }, secondList.map { it.toPair() })
    .map { it.toIntArray() }
    .toTypedArray()

fun intervalListIntersections(
  a: List<Pair<Int, Int>>,
  b: List<Pair<Int, Int>>,
): List<Pair<Int, Int>> {
  tailrec fun intervalListIntersections(
    aIndex: Int,
    bIndex: Int,
    result: List<Pair<Int, Int>> = emptyList(),
  ): List<Pair<Int, Int>> {
    if (aIndex > a.lastIndex || bIndex > b.lastIndex) return result

    // ! This way we can be agnostic of which interval in which list starts first
    val latestStart = maxOf(a[aIndex].first, b[bIndex].first)
    val earliestEnd = minOf(a[aIndex].second, b[bIndex].second)

    val intervalIntersections =
      when {
        latestStart <= earliestEnd -> result + (latestStart to earliestEnd)
        else -> result // ! No Intersection
      }

    // ! Move past earliest ending Job
    return when {
      a[aIndex].second == b[bIndex].second ->
        intervalListIntersections(aIndex + 1, bIndex + 1, intervalIntersections)
      a[aIndex].second < b[bIndex].second ->
        intervalListIntersections(aIndex + 1, bIndex, intervalIntersections)
      else -> intervalListIntersections(aIndex, bIndex + 1, intervalIntersections)
    }
  }

  return intervalListIntersections(0, 0)
}
