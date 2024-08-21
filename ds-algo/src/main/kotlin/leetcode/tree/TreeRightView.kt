package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.rightSideView(result: MutableList<Int> = mutableListOf(), depth: Int = 0): List<Int> {
  if (
    result.size == depth
  ) { // Adding a value at every level of depth. So if there is no right, left takes that place.
    result.add(value)
  }
  right?.rightSideView(result, depth + 1)
  left?.rightSideView(result, depth + 1)

  return result
}
