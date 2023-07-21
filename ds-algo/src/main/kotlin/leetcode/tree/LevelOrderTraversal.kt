package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode

/** https://leetcode.com/problems/binary-tree-level-order-traversal */
tailrec fun TreeNode.levelOrderTraversal(
  currentLevel: List<TreeNode> = listOf(this),
  result: List<List<TreeNode>> = listOf(currentLevel)
): List<List<TreeNode>> {
  val nextLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
  return if (nextLevel.isNotEmpty()) {
    levelOrderTraversal(nextLevel, result.plusElement(nextLevel))
  } else {
    result
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
