package educative.elegant.unionfind

/* 26 Sep 2024 16:34 */
fun longestConsecutiveSequence(nums: IntArray): Int {
  val unionFind = UnionFind6(nums)
  // * We are directly using the num values, no indexes being used
  nums.asSequence().filter { (it + 1) in unionFind.roots }.forEach { unionFind.union(it, it + 1) }
  return unionFind.ranks.values.maxOrNull() ?: 0
}

private class UnionFind6(nums: IntArray) {
  // ! Using `map` instead of `IntArray`, to use nums directly without indices
  // ! Also helps to search for `num + 1`
  val roots = nums.associateWith { it }.toMutableMap()
  // ! `1` as we use ranks to store sequence length and each element is its own sequence
  val ranks = nums.associateWith { 1 }.toMutableMap()

  tailrec fun find(n: Int): Int {
    val root = roots[n]!!
    return when {
      n == root -> root
      else -> find(root)
    }
  }

  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    if (root1 != root2) {
      // * Here rank is used to measure `size` of a set.
      // * So we are adding ranks instead of just incrementing by 1
      when {
        ranks[root1]!! >= ranks[root2]!! -> {
          roots[root2] = root1
          ranks.computeIfPresent(root1) { _, value -> value.plus(ranks[root2]!!) }
        }
        else -> {
          roots[root1] = root2
          ranks.computeIfPresent(root2) { _, value -> value.plus(ranks[root1]!!) }
        }
      }
    }
  }
}
