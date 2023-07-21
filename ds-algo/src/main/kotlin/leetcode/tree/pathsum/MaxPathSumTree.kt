/* gakshintala created on 1/18/20 */
package leetcode.tree.pathsum

import ga.overfullstack.ds.tree.TreeNode

/** https://leetcode.com/problems/binary-tree-maximum-path-sum/ */
fun TreeNode.maxPathSum(): Pair<Int, Int> {
  val (leftSum, maxSumInLeft) = left?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  val (rightSum, maxSumInRight) = right?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  // maxOf(0,...) is to avoid anything that lessens the sum, any negative numbers
  val sumWithRoot = maxOf(0, leftSum) + maxOf(0, rightSum) + value
  val curMaxSum = maxOf(sumWithRoot, maxSumInLeft, maxSumInRight)

  val maxSumInPath = maxOf(0, leftSum, rightSum) + value

  // pick from left or right, compare current sum with left/right.
  // In the below pair, first is like you are open to check sum with upper roots
  // Second is more of a temp variable hodling current max.
  return maxSumInPath to curMaxSum
}
