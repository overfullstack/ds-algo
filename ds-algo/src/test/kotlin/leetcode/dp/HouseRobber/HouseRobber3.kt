package leetcode.dp.HouseRobber

import ds.tree.TreeNode

fun rob3(root: TreeNode?): Int {
  val result = root?.robUtil() ?: 0 to 0
  return maxOf(result.first, result.second)
}

private fun TreeNode?.robUtil(): Pair<Int, Int> {
  if (this == null) {
    return 0 to 0
  }
  val (withoutLeft, withLeft) = left.robUtil()
  val (withoutRight, withRight) = right.robUtil()

  // If root is excluded you get all 4 combinations with, without, left, right
  val excludingRoot = maxOf(withLeft, withoutLeft) + maxOf(withRight, withoutRight)
  val includingRoot = value + withoutLeft + withoutRight

  return excludingRoot to includingRoot
}
