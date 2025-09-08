package leetcode.graph.dfs

import utils.toTriple

/* 21 Aug 2025 08:24 */

/** [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs/) */
fun maximumDetonation(bombs: Array<IntArray>): Int {
  val bombs1 = bombs.map { it.toTriple() }
  return bombs1.indices.maxOf { dfsInRange(it, bombs1) }
}

fun dfsInRange(
  fromBombIdx: Int,
  bombs: List<Triple<Int, Int, Int>>,
  visitedPerGroup: MutableSet<Int> = mutableSetOf(),
): Int {
  visitedPerGroup += fromBombIdx
  return 1 +
    bombs.indices
      .asSequence()
      .filter { it !in visitedPerGroup && inRange(bombs[it], bombs[fromBombIdx]) }
      .sumOf { dfsInRange(it, bombs, visitedPerGroup) }
}

fun inRange(toBomb: Triple<Int, Int, Int>, fromBomb: Triple<Int, Int, Int>): Boolean {
  val dx = (toBomb.first - fromBomb.first).toLong()
  val dy = (toBomb.second - fromBomb.second).toLong()
  val radiusSquared = fromBomb.third.toLong() * fromBomb.third
  return (dx * dx + dy * dy) <= radiusSquared
}

fun main() {
  println(
    maximumDetonation(
      arrayOf(
        intArrayOf(56, 80, 2),
        intArrayOf(55, 9, 10),
        intArrayOf(32, 75, 2),
        intArrayOf(87, 89, 1),
        intArrayOf(61, 94, 3),
        intArrayOf(43, 82, 9),
        intArrayOf(17, 100, 6),
        intArrayOf(50, 6, 7),
        intArrayOf(9, 66, 7),
        intArrayOf(98, 3, 6),
        intArrayOf(67, 50, 2),
        intArrayOf(79, 39, 5),
        intArrayOf(92, 60, 10),
        intArrayOf(49, 9, 9),
        intArrayOf(42, 32, 10),
      )
    )
  ) // Expected: 3
}
