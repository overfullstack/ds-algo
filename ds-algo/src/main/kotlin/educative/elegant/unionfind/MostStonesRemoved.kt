package educative.elegant.unionfind

/* 07 Sep 2024 13:53 */
fun mostStonesRemoved(stones: Set<Pair<Int, Int>>): Int {
  val unionFind = UnionFind2(stones)
  return stones.size - unionFind.groups // By leaving only 1 per group
}

private class UnionFind2(stones: Set<Pair<Int, Int>>) {
  val roots = mutableMapOf<Pair<Int, Char>, Pair<Int, Char>>()
  val ranks = mutableMapOf<Pair<Int, Char>, Int>()
  val groups: Int
    get() = roots.values.distinctBy { find(it) }.count()

  init {
    stones.forEach { (x, y) -> union(x, y) }
  }

  /**
   * Group cell to another cell in the same row or col, whichever has the highest rank. Ranks are
   * only used to optimize find
   */
  fun union(x: Int, y: Int) {
    val xx = x to 'x' // E.g., 2x represents 2nd row; all stones in this row are grouped
    roots.putIfAbsent(xx, xx)
    ranks.putIfAbsent(xx, 0)
    val yy = y to 'y'
    roots.putIfAbsent(yy, yy)
    ranks.putIfAbsent(yy, 0)

    val rootXX = find(xx)
    val rootYY = find(yy)
    if (rootXX != rootYY) {
      when {
        ranks[rootXX]!! > ranks[rootYY]!! -> roots[yy] = rootXX
        ranks[rootXX]!! < ranks[rootYY]!! -> roots[xx] = rootYY
        else -> {
          roots[rootXX] = rootYY
          ranks.compute(rootYY) { _, value -> value?.plus(1) }
        }
      }
    }
  }

  tailrec fun find(toFind: Pair<Int, Char>): Pair<Int, Char> =
    when {
      roots[toFind] == toFind -> toFind
      else -> find(roots[toFind]!!)
    }
}
