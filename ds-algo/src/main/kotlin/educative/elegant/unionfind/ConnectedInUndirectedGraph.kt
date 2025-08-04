package educative.elegant.unionfind

/* 22 Jul 2025 19:38 */

fun countComponents(n: Int, edges: List<Pair<Int, Int>>): Int {
  val unionFind = UnionFind7(n)
  return n - edges.count { unionFind.union(it.first, it.second) }
}

private class UnionFind7(n: Int) {
  val roots = IntArray(n) { it }
  val ranks = IntArray(n)

  tailrec fun find(n: Int): Int {
    val root = roots[n]
    return when {
      n == root -> root
      else -> find(root)
    }
  }

  fun union(n1: Int, n2: Int): Boolean {
    val root1 = find(n1)
    val root2 = find(n2)
    if (root1 != root2) {
      when {
        ranks[root1] > ranks[root2] -> roots[root2] = root1
        ranks[root2] > ranks[root1] -> roots[root1] = root2
        else -> {
          roots[root1] = root2
          ranks[root2]++
        }
      }
      return true
    }
    return false
  }
}
