package educative.backtracking

import ds.tree.TreeNode

fun TreeNode.houseRobber3(): Int {
  val (sumWithRoot, sumWithoutRoot) = houseRobber3Internal()
  return maxOf(sumWithRoot, sumWithoutRoot)
}

fun TreeNode.houseRobber3Internal(): Pair<Int, Int> {
  val (leftSumWithRoot, leftSumWithoutRoot) = left?.houseRobber3Internal() ?: (0 to 0)
  val (rightSumWithRoot, rightSumWithoutRoot) = right?.houseRobber3Internal() ?: (0 to 0)

  val sumWithRoot = value + leftSumWithoutRoot + rightSumWithoutRoot
  val sumWithoutRoot =
    maxOf(leftSumWithRoot, leftSumWithoutRoot) + maxOf(rightSumWithRoot, rightSumWithoutRoot)

  return sumWithRoot to sumWithoutRoot
}
