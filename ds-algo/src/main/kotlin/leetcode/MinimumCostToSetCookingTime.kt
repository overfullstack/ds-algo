package leetcode

/* 04 Aug 2025 16:59 */

/**
 * [2162. Minimum Cost to Set Cooking
 * Time](https://leetcode.com/problems/minimum-cost-to-set-cooking-time)
 */
fun minCostSetTime(startAt: Int, moveCost: Int, pushCost: Int, targetSeconds: Int): Int =
  (0..targetSeconds / 60)
    .map { it to targetSeconds - it * 60 }
    .filter { (minutes, seconds) -> minutes <= 99 && seconds <= 99 }
    .minOf { (minutes, seconds) -> cost(startAt, minutes, seconds, moveCost, pushCost) }

fun cost(startAt: Int, minutes: Int, seconds: Int, moveCost: Int, pushCost: Int): Int =
  "$startAt${minutes * 100 + seconds}".zipWithNext().fold(0) { cost, (ch, nextCh) ->
    when {
      ch == nextCh -> cost + pushCost
      else -> cost + moveCost + pushCost
    }
  }

fun main() {
  println(minCostSetTime(1, 2, 1, 600)) // 6
  println(minCostSetTime(0, 1, 2, 76)) // 6
}
