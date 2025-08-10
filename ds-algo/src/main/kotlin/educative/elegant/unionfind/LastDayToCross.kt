package educative.elegant.unionfind

/* 09 Sep 2024 18:23 */
fun lastDayToCross(rows: Int, cols: Int, waterCells: Set<Pair<Int, Int>>): Int {
  val unionFind = UnionFind3(rows, cols)
  return waterCells
    .fold(emptySet<Pair<Int, Int>>()) { waterDayByDay, waterToday ->
      connectWaterCells(waterToday, rows, cols, waterDayByDay, unionFind)
      when {
        // `0` and `rows * cols + 1` are virtual nodes on either end horizontally
        // If an e2e water cell connection exists horizontally, we are blocked to cross vertically
        unionFind.find(0) == unionFind.find(rows * cols + 1) -> return waterDayByDay.size
        else -> waterDayByDay + waterToday
      }
    }
    .size
}

// ! We can move in only 4 directions, but waterCells can be connected diagonally also
// ! to block the path horizontally, so 8 directions
private val directions =
  listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, 1 to 1, -1 to 1, 1 to -1, -1 to -1)

private fun connectWaterCells(
  waterToday: Pair<Int, Int>,
  rows: Int,
  cols: Int,
  waterDayByDay: Set<Pair<Int, Int>>,
  unionFind: UnionFind3,
) {
  directions
    .asSequence()
    .map { waterToday.first + it.first to waterToday.second + it.second }
    .filter { isValid(it, rows, cols, waterDayByDay) }
    .forEach { unionFind.union(waterToday, it) }
  // For edges, connect with virtual nodes
  when (waterToday.second) {
    1 -> unionFind.union(0, waterToday)
    cols -> unionFind.union(rows * cols + 1, waterToday)
  }
}

fun isValid(
  cell: Pair<Int, Int>,
  rows: Int,
  cols: Int,
  waterDayByDay: Set<Pair<Int, Int>>,
): Boolean = cell.first in (1..rows) && cell.second in (1..cols) && cell in waterDayByDay

private class UnionFind3(rows: Int, val cols: Int) {
  // ! +2 for virtual nodes added on either ends horizontally
  val roots = IntArray(rows * cols + 2) { it }
  val ranks = IntArray(rows * cols + 2)

  tailrec fun find(n: Int): Int =
    when {
      roots[n] == n -> n
      else -> find(roots[n])
    }

  fun union(n1: Pair<Int, Int>, n2: Pair<Int, Int>) =
    union((n1.first - 1) * cols + n1.second - 1, (n2.first - 1) * cols + n2.second - 1)

  fun union(n1: Int, n2: Pair<Int, Int>) = union(n1, (n2.first - 1) * cols + n2.second - 1)

  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    if (root1 != root2) {
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
}
