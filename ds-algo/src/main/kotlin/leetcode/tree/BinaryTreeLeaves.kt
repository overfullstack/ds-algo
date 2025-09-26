package leetcode.tree

import ds.tree.TreeNode

/* 05 Aug 2025 22:49 */

/**
 * [366 - Find Leaves of Binary
 * Tree](https://leetcode.ca/2016-11-30-366-Find-Leaves-of-Binary-Tree/)
 */
fun findLeaves(root: TreeNode): List<List<Int>> {
  val dummyRootParent = TreeNode(0, root)
  return generateSequence {
      if (dummyRootParent.left != null) root.findLeaves(dummyRootParent) else null
    }
    .toList()
}

fun TreeNode.findLeaves(parent: TreeNode, isLeft: Boolean = true): List<Int> =
  when (left) {
    null if right == null -> {
      when (isLeft) {
        true -> parent.left = null
        else -> parent.right = null
      }
      listOf(`val`)
    }
    else -> listOfNotNull(left?.findLeaves(this), right?.findLeaves(this, false)).flatten()
  }

fun main() {
  val root = TreeNode.levelOrderToIncompleteTree(listOf(1, 2, 3, 4, 5))!!
  println(findLeaves(root)) // [[4, 5, 3], [2], [1]]
}
