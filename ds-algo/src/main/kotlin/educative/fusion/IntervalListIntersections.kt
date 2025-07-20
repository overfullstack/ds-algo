package educative.fusion

/* 20 Jul 2025 16:12 */

fun intervalListIntersections(
  a: List<Pair<Int, Int>>,
  b: List<Pair<Int, Int>>,
): List<Pair<Int, Int>> {
  tailrec fun findIntersections(
    aIndex: Int,
    bIndex: Int,
    result: List<Pair<Int, Int>>,
  ): List<Pair<Int, Int>> {
    if (aIndex > a.lastIndex || bIndex > b.lastIndex) return result

    val latestStart = maxOf(a[aIndex].first, b[bIndex].first)
    val earliestEnd = minOf(a[aIndex].second, b[bIndex].second)

    val intervalIntersections =
      when {
        latestStart <= earliestEnd -> result + (latestStart to earliestEnd)
        else -> result
      }

    return when {
      a[aIndex].second == b[bIndex].second ->
        findIntersections(aIndex + 1, bIndex + 1, intervalIntersections)
      a[aIndex].second < b[bIndex].second ->
        findIntersections(aIndex + 1, bIndex, intervalIntersections)
      else -> findIntersections(aIndex, bIndex + 1, intervalIntersections)
    }
  }

  return findIntersections(0, 0, emptyList())
}
