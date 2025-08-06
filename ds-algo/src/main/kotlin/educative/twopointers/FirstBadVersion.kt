package educative.twopointers

/* 06 Aug 2025 12:54 */

/** [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) */
class FirstBadVersion(val firstBadVersionIndex: Int) {

  private fun isBadVersion(indexToCheck: Int) = indexToCheck >= firstBadVersionIndex

  fun firstBadVersion(n: Int): Pair<Int, Int> {
    var left = 1
    var right = n
    var callCount = 0
    while (left < right) {
      val mid = left + (right - left) / 2
      when {
        isBadVersion(mid) -> right = mid
        else -> left = mid + 1
      }
      callCount++
    }
    return right to callCount
  }
}

fun main() {
  val firstBadVersion = FirstBadVersion(10)
  println(firstBadVersion.firstBadVersion(13))
}
