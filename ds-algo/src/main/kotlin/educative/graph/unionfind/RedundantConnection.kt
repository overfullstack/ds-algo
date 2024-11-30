package educative.graph.unionfind

/* 16 Sep 2024 15:47 */
fun redundantConnection(edges: List<Pair<Int, Int>>): Pair<Int, Int> {
  val max = edges.flatMap { it.toList() }.max()
  val unionFind = UnionFind5(max)
  return edges.first { !unionFind.union(it) }
}

private class UnionFind5(size: Int) {
  val roots = Array<Int>(size + 1) { it }
  val ranks = Array<Int>(size + 1) { 0 }

  tailrec fun find(n: Int): Int =
    when {
      roots[n] == n -> n
      else -> find(roots[n])
    }

  fun union(n: Pair<Int, Int>): Boolean {
    val root1 = find(n.first)
    val root2 = find(n.second)
    return when {
      root1 != root2 -> {
        when {
          ranks[root1] > ranks[root2] -> roots[root2] = root1
          ranks[root2] > ranks[root1] -> roots[root1] = root2
          else -> {
            roots[root1] = root2
            ranks[root2]++
          }
        }
        true
      }
      else -> false
    }
  }
}
