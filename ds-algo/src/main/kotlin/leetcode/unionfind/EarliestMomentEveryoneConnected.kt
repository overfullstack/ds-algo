package leetcode.unionfind

/* 04 Aug 2025 10:49 */

/**
 * [1101 - The Earliest Moment When Everyone Become
 * Friends](https://leetcode.ca/2018-12-05-1101-The-Earliest-Moment-When-Everyone-Become-Friends/)
 */
fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
  // ! Sorted by timestamp
  val sortedLogs = logs.sortedBy { it[0] }
  val unionFind = UnionFind(n)
  return sortedLogs
    .asSequence()
    .takeWhile { unionFind.groupCount != 1 }
    .onEach { (_, u, v) -> unionFind.union(u, v) }
    .last()[0] // ! last after takeWhile returns false
}

private class UnionFind(size: Int) {
  val roots = IntArray(size) { it }
  val ranks = IntArray(size)
  var groupCount = size

  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    if (root1 != root2) {
      when {
        ranks[root1] > ranks[root2] -> roots[root1] = root2
        ranks[root2] > ranks[root1] -> roots[root2] = root1
        else -> {
          roots[root1] = root2
          ranks[root2]++
        }
      }
      groupCount--
    }
  }

  tailrec fun find(n: Int): Int {
    val root = roots[n]
    return when {
      root == n -> root
      else -> find(root)
    }
  }
}

fun main() {
  println(
    earliestAcq(
      arrayOf(
        intArrayOf(20190101, 0, 1),
        intArrayOf(20190104, 3, 4),
        intArrayOf(20190107, 2, 3),
        intArrayOf(20190211, 1, 5),
        intArrayOf(20190224, 2, 4),
        intArrayOf(20190301, 0, 3),
        intArrayOf(20190312, 1, 2),
        intArrayOf(20190322, 4, 5),
      ),
      6,
    ) // 20190301
  )
  println(
    earliestAcq(
      arrayOf(
        intArrayOf(0, 2, 0),
        intArrayOf(1, 0, 1),
        intArrayOf(3, 0, 3),
        intArrayOf(4, 1, 2),
        intArrayOf(7, 3, 1),
      ),
      4,
    ) // 3
  )
}
