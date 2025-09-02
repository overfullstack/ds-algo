package educative.twopointers

/* 06 Aug 2025 12:54 */

/** [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) */
class FirstBadVersion(val firstBadVersionIndex: Int) {

  private fun isBadVersion(indexToCheck: Int) = indexToCheck >= firstBadVersionIndex

  tailrec fun firstBadVersion(
    n: Int,
    callCount: Int = 0,
    left: Int = 1,
    right: Int = n,
  ): Pair<Int, Int> =
    when {
      left == right -> right to callCount
      else -> {
        val mid = left + (right - left) / 2
        when {
          isBadVersion(mid) -> firstBadVersion(n, callCount + 1, left, mid)
          else -> firstBadVersion(n, callCount + 1, mid + 1, right)
        }
      }
    }

  // * Leftmost where condition is true
  fun firstBadVersionIterative(n: Int): Pair<Int, Int> {
    var left = 1
    var right = n
    var callCount = 0
    while (left < right) {
      val mid = left + (right - left) / 2
      when {
        // ! `right` stays on mid where the condition is true
        isBadVersion(mid) -> right = mid // ! Condition over right
        else -> left = mid + 1
      }
      callCount++
    }
    return right to callCount // return `right`
  }
}

fun main() {
  val firstBadVersion = FirstBadVersion(2)
  println(firstBadVersion.firstBadVersion(5))
}
