package leetcode.tree

import ds.tree.TreeNode

/**
 * [102. Binary Tree Level Order
 * Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal)
 */
tailrec fun TreeNode.levelOrderTraversal(
  currentLevel: List<TreeNode> = listOf(this),
  result: List<List<TreeNode>> = listOf(currentLevel),
): List<List<TreeNode>> {
  val nextLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
  return when {
    nextLevel.isEmpty() -> result
    // * Next level passed as current level for next iteration
    else -> levelOrderTraversal(nextLevel, result.plusElement(nextLevel))
  }
}

fun TreeNode.levelOrderTraversalIterative(): List<List<TreeNode>> {
  var currentLevel = listOf(this)
  val result = mutableListOf<List<TreeNode>>()

  while (currentLevel.isNotEmpty()) {
    result += currentLevel
    currentLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
  }
  return result
}
