package educative.graph.unionfind

/* 09 Sep 2024 18:23 */
fun lastDayToCross(rows: Int, cols: Int, waterCells: Set<Pair<Int, Int>>): Int {
  val unionFind = UnionFind3(rows, cols)
  return waterCells.fold(emptySet<Pair<Int, Int>>()) { waterDayByDay, waterToday ->
    union(waterToday, rows, cols, waterDayByDay, unionFind)
    if (unionFind.find(0) == unionFind.find(rows * cols + 1)) {
      return waterDayByDay.size
    }
    waterDayByDay + waterToday
  }.size
}

private val directions =
  listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, 1 to 1, -1 to 1, 1 to -1, -1 to -1)

private fun union(
  n: Pair<Int, Int>,
  rows: Int,
  cols: Int,
  waterDayByDay: Set<Pair<Int, Int>>,
  unionFind: UnionFind3
) {
  directions
    .asSequence()
    .map { n.first + it.first to n.second + it.second }
    .filter { isValid(it, rows, cols, waterDayByDay) }
    .forEach { unionFind.union(n, it) }
  when (n.second) {
    1 -> unionFind.union(0, n)
    cols -> unionFind.union(rows * cols + 1, n)
  }
}

fun isValid(n: Pair<Int, Int>, rows: Int, cols: Int, waterDayByDay: Set<Pair<Int, Int>>): Boolean =
  n.first in (1..rows) && n.second in (1..cols) && n in waterDayByDay

private class UnionFind3(rows: Int, val cols: Int) {
  val roots = Array<Int>(rows * cols + 2) { it }
  val ranks = Array<Int>(rows * cols + 2) { 0 }

  tailrec fun find(n: Int): Int =
    when {
      roots[n] == n -> n
      else -> find(roots[n])
    }

  fun union(n1: Pair<Int, Int>, n2: Pair<Int, Int>) =
    union((n1.first - 1) * cols + n1.second - 1, (n2.first - 1) * cols + n2.second - 1)

  fun union(n1: Int, n2: Pair<Int, Int>) =
    union(n1, (n2.first - 1) * cols + n2.second - 1)

  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    when {
      ranks[root1] < ranks[root2] -> roots[root1] = root2
      ranks[root1] > ranks[root2] -> roots[root2] = root1
      else -> {
        roots[root1] = root2
        ranks[root2]++
      }
    }
  }
}

fun main() {
  val water = setOf(1 to 2, 2 to 1, 3 to 3, 2 to 2, 1 to 1, 1 to 3, 2 to 3, 3 to 2, 3 to 1)
  println(lastDayToCross(3, 3, water))
}
