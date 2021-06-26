package leetcode.dp

fun numTrees(n: Int): Int {
  val table = IntArray(n + 1)
  table[0] = 1
  table[1] = 1
  // Building up by increasing number of elements in the tree.
  for (nodeCount in 2..n) {
    // Only `nodeCount - 1` elements are shuffled both sides `(it - 1) + (nodeCount - it)`. The missing one is for root.
    // Increasing left side * Decreasing right side
    table[nodeCount] = (1..nodeCount).map { table[it - 1] * table[nodeCount - it] }.sum()
  }
  return table[n]
}
