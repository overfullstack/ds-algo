package educative.tree

import ds.tree.TreeNode

/** [337. House Robber III](https://leetcode.com/problems/house-robber-iii/) */
fun TreeNode.houseRobber3(): Int {
  val (sumWithRoot, sumWithoutRoot) = houseRobber3Internal()
  return maxOf(sumWithRoot, sumWithoutRoot)
}

fun TreeNode.houseRobber3Internal(): Pair<Int, Int> {
  // * At any point we have access to 3 levels of data -
  // * cur, left, right, rest of the tree under
  // * current, left (with, without Root), right (with, without Root)
  val (leftSumWithRoot, leftSumWithoutRoot) = left?.houseRobber3Internal() ?: (0 to Int.MIN_VALUE)
  val (rightSumWithRoot, rightSumWithoutRoot) =
    right?.houseRobber3Internal() ?: (0 to Int.MIN_VALUE)

  // ! With root is easy, you need to pick the skip level
  val sumWithRoot = `val` + leftSumWithoutRoot + rightSumWithoutRoot
  // ! Without root, you have 2 choices on either side (1 per level), pick the max from each
  val sumWithoutRoot =
    maxOf(leftSumWithRoot, leftSumWithoutRoot) + maxOf(rightSumWithRoot, rightSumWithoutRoot)

  return sumWithRoot to sumWithoutRoot
}
