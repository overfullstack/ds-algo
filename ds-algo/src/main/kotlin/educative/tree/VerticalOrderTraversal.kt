package educative.tree

import ds.tree.TreeNode

/* 19 Jul 2025 17:55 */

fun TreeNode.verticalTraversal(): List<List<Int>> =
  verticalTraversalInternal()
    .flatten()
    .groupBy({ it.first }, { it.second.value })
    .toSortedMap()
    .values
    .toList()

private tailrec fun TreeNode.verticalTraversalInternal(
  currentLevel: List<Pair<Int, TreeNode>> = listOf(0 to this),
  result: List<List<Pair<Int, TreeNode>>> = listOf(currentLevel),
): List<List<Pair<Int, TreeNode>>> {
  val nextLevel =
    currentLevel.flatMap { (colIndex, node) ->
      listOfNotNull(node.left?.let { colIndex - 1 to it }, node.right?.let { colIndex + 1 to it })
    }
  return when {
    nextLevel.isEmpty() -> result
    else -> verticalTraversalInternal(nextLevel, result.plusElement(nextLevel))
  }
}
