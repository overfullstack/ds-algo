package educative.graph.unionfind

/* 07 Sep 2024 13:53 */
fun mostStonesRemoved(stones: Set<Pair<Int, Int>>): Int {
  val unionFind = UnionFind2(stones)
  return stones.size - unionFind.groups
}

private class UnionFind2(stones: Set<Pair<Int, Int>>) {
  val roots = mutableMapOf<Pair<Int, Char>, Pair<Int, Char>>()
  val ranks = mutableMapOf<Pair<Int, Char>, Int>()
  val groups: Int
    get() = roots.keys.distinctBy { find(it) }.count()

  init {
    stones.forEach { (x, y) -> union(x, y) }
  }

  fun union(x: Int, y: Int) {
    val xx = x to 'x'
    roots.putIfAbsent(xx, xx)
    ranks.putIfAbsent(xx, 0)
    val yy = y to 'y'
    roots.putIfAbsent(yy, yy)
    ranks.putIfAbsent(yy, 0)

    val rootXX = find(xx)
    val rootYY = find(yy)
    when {
      ranks[rootXX]!! > ranks[rootYY]!! -> roots[yy] = rootXX
      ranks[rootXX]!! < ranks[rootYY]!! -> roots[xx] = rootYY
      else -> {
        roots[rootXX] = rootYY
        ranks.compute(rootXX) { _, value -> value?.plus(1) }
      }
    }
  }

  tailrec fun find(toFind: Pair<Int, Char>): Pair<Int, Char> =
    when {
      roots[toFind] == toFind -> toFind
      else -> find(roots[toFind]!!)
    }
}
