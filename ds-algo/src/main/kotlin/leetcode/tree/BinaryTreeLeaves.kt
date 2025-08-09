package leetcode.tree

import ds.tree.TreeNode

/* 05 Aug 2025 22:49 */

/**
 * [366 - Find Leaves of Binary Tree](https://leetcode.ca/2016-11-30-366-Find-Leaves-of-Binary-Tree/)
 */
fun findLeaves(root: TreeNode): List<List<Int>> {
  val dummyRootParent = TreeNode(0, root)
  return generateSequence {
    if (dummyRootParent.left != null) findLeaves(root, dummyRootParent) else null
  }.toList()
}

fun findLeaves(root: TreeNode, parent: TreeNode): List<Int> =
  when (root.left) {
      null if root.right == null -> {
        when {
          parent.left == root -> parent.left = null
          else -> parent.right = null
        }
        listOf(root.`val`)
      }
      else -> listOfNotNull(root.left, root.right).flatMap { findLeaves(it, root) }
  }

fun main() {
  val root = TreeNode.levelOrderToIncompleteTree(listOf(1, 2, 3, 4, 5))!!
  println(findLeaves(root)) // [[4, 5, 3], [2], [1]]
}
