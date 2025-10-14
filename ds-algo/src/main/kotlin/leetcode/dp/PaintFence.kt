/* gakshintala created on 9/24/19 */
package leetcode.dp

/** [514 · Paint Fence](https://www.lintcode.com/problem/514) */
fun numWays(n: Int, k: Int): Int =
  when (k) {
    0 -> 0
    1 -> if (n == 1 || n == 2) 1 else 0
    else -> solve(0, n, k, IntArray(n))
  }

private fun solve(idx: Int, n: Int, k: Int, memo: IntArray): Int =
  when {
    idx == n - 2 -> k * k // ! For only 2 left, so `k` ways to paint each, as they can be same color
    idx == n - 1 -> k
    memo[idx] != 0 -> memo[idx]
    // ! (k - 1) as 3 consecutive can't have same color
    else ->
      (k - 1) * (solve(idx + 2, n, k, memo) + solve(idx + 1, n, k, memo)).also { memo[idx] = it }
  }

fun numWaysBottomUp(noOfSlots: Int, numOfColors: Int): Int {
  var paintSameColor: Int
  var paintDifferentColor = numOfColors // For Single slot
  var totalNoOfWaysToPaintTillNow = numOfColors // For Single slot
  repeat(noOfSlots - 1) {
    // Atmost 2 posts can have the same color, so painting this with last one with same color is
    // same as
    // painting (n-1) with different colors.
    // = `paintDifferentColor` instead of `totalNoOfWaysToPaintTillNow`
    // If `n` and `n-1` hv same color, then `n-1` and `n-2` must have different colors. So not
    // considering paintsame from previous.
    paintSameColor = paintDifferentColor
    paintDifferentColor =
      totalNoOfWaysToPaintTillNow * (numOfColors - 1) // Except the previous slot color
    totalNoOfWaysToPaintTillNow = paintSameColor + paintDifferentColor
  }
  return totalNoOfWaysToPaintTillNow
}

fun main() {
  println(numWays(3, 2)) // 6
  println(numWays(2, 2)) // 4
  println(numWays(1, 1)) // 1
}
