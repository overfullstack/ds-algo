package algoexpert.tree

import ds.tree.TreeNode

tailrec fun TreeNode.nodeDepthsSum(
  currentLevel: List<TreeNode> = listOf(this),
  depth: Int = 0,
  result: Int = 0
): Int {
  val nextLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
  if (nextLevel.isEmpty()) {
    return result
  }
  val nextDepth = depth + 1
  return nodeDepthsSum(nextLevel, nextDepth, result + nextDepth * nextLevel.size)
}
