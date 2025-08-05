package leetcode.tree

import ds.tree.TreeNode


/* 05 Aug 2025 22:49 */

fun findLeaves(root: TreeNode): List<List<Int>> {
  val result = mutableListOf<List<Int>>()
  val prev = TreeNode(0, root, null)
  while(prev.left != null) {
    val t = mutableListOf<Int>()
    findLeaves(root, prev, t)
    result.add(t)
  }
  return result
}

fun findLeaves(root: TreeNode, prev: TreeNode, t: MutableList<Int>) {
    if (root.left == null && root.right == null) {
      when {
        prev.left == root -> prev.left = null
        else -> prev.right = null
      }
      t.add(root.`val`)
    }
  root.left?.let { findLeaves(it, root, t) }
  root.right?.let { findLeaves(it, root, t) }
}

fun main() {
  val root = TreeNode.levelOrderToIncompleteTree(listOf(1,2,3,4,5))!!
  println(findLeaves(root))
}
