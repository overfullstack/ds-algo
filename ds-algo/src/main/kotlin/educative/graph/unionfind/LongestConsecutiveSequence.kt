package educative.graph.unionfind

/* 26 Sep 2024 16:34 */
fun longestConsecutiveSequenceKt(nums: IntArray): Int {
  val unionFind = UnionFind6(nums)
  nums.asSequence().filter { (it + 1) in unionFind.roots }.forEach { unionFind.union(it, it + 1) }
  return unionFind.ranks.max()
}

private class UnionFind6(val nums: IntArray) {
  val roots = nums.withIndex().associate { it.value to it.index }.toMutableMap()
  val ranks = Array<Int>(nums.size) { 1 }

  tailrec fun find(n: Int): Int {
    val rootIndex = roots[n]!!
    return when {
      n == nums[rootIndex] -> rootIndex
      else -> find(nums[rootIndex])
    }
  }

  fun union(n1: Int, n2: Int) {
    val root1Index = find(n1)
    val root2Index = find(n2)
    if (root1Index != root2Index) {
      when {
        ranks[root1Index] >= ranks[root2Index] -> {
          roots[nums[root2Index]] = root1Index
          ranks[root1Index] += ranks[root2Index]
        }
        else -> {
          roots[nums[root1Index]] = root2Index
          ranks[root2Index] += ranks[root1Index]
        }
      }
    }
  }
}
