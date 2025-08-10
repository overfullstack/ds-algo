package leetcode.tree

import ds.tree.TreeNode

/* 10 Aug 2025 11:51 */

/**
 * [1110. Delete Nodes And Return
 * Forest](https://leetcode.com/problems/delete-nodes-and-return-forest/)
 */
fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> =
  root?.findAndDelete(null, to_delete.toSet()) ?: emptyList()

fun TreeNode.findAndDelete(
  parent: TreeNode?,
  toDelete: Set<Int>,
  isParentDeleted: Boolean = true,
  isLeft: Boolean = true,
): List<TreeNode> {
  val toBeDeleted = `val` in toDelete
  if (toBeDeleted) {
    when (isLeft) {
      true -> parent?.left = null
      else -> parent?.right = null
    }
  }
  return (if (isParentDeleted && !toBeDeleted) listOf(this) else emptyList()) +
    listOfNotNull(
        left?.findAndDelete(this, toDelete, toBeDeleted),
        right?.findAndDelete(this, toDelete, toBeDeleted, false),
      )
      .flatten()
}

fun main() {
  // root = [1,2,3,4,5,6,7], to_delete = [3,5]
  val root = TreeNode.levelOrderToIncompleteTree(listOf(1, 2, 3, 4, 5, 6, 7))!!
  delNodes(root, intArrayOf(3, 5)).map { it!!.incompleteTreeToLevelOrderList() }.forEach(::println)
}
