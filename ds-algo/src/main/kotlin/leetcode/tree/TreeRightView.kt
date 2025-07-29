package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.rightSideView(result: MutableList<Int> = mutableListOf(), depth: Int = 0): List<Int> {
  // * Adding the first value at every level of depth.
  // So if there is no right, left takes that place.
  if (result.size == depth) {
    result.add(value)
  }
  // ! Notice `right` before `left`
  right?.rightSideView(result, depth + 1)
  left?.rightSideView(result, depth + 1)

  return result
}

fun TreeNode.rightSideView2(
  currentLevel: List<TreeNode> = listOf(this),
  result: List<Int> = listOf(value),
): List<Int> {
  val nextLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
  return when {
    nextLevel.isEmpty() -> result
    else -> rightSideView2(nextLevel, result.plusElement(nextLevel.last().value))
  }
}
