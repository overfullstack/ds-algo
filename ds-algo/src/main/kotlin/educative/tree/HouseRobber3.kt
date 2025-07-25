package educative.tree

import ds.tree.TreeNode

fun TreeNode.houseRobber3(): Int {
  val (sumWithRoot, sumWithoutRoot) = houseRobber3Internal()
  return maxOf(sumWithRoot, sumWithoutRoot)
}

fun TreeNode.houseRobber3Internal(): Pair<Int, Int> {
  // * At any point we have access to 3 levels of data -
  // * cur, left, right, rest of the tree under left/right
  // * current, left (with, without Root), right (with, without Root)
  val (leftSumWithRoot, leftSumWithoutRoot) = left?.houseRobber3Internal() ?: (0 to Int.MIN_VALUE)
  val (rightSumWithRoot, rightSumWithoutRoot) =
    right?.houseRobber3Internal() ?: (0 to Int.MIN_VALUE)

  val sumWithRoot = value + leftSumWithoutRoot + rightSumWithoutRoot
  val sumWithoutRoot =
    maxOf(leftSumWithRoot, leftSumWithoutRoot) + maxOf(rightSumWithRoot, rightSumWithoutRoot)

  return sumWithRoot to sumWithoutRoot
}
