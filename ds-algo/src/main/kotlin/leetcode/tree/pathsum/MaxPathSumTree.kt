/* gakshintala created on 1/18/20 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

/** https://leetcode.com/problems/binary-tree-maximum-path-sum/ */
fun TreeNode.maxPathSum(): Pair<Int, Int> {
  val (leftSum, maxSumInLeft) = left?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  val (rightSum, maxSumInRight) = right?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  // * `coerceAtLeast(0)` is to avoid any negative number that lessens the sum
  val sumWithRoot = leftSum.coerceAtLeast(0) + rightSum.coerceAtLeast(0) + value
  val curMaxSum = maxOf(sumWithRoot, maxSumInLeft, maxSumInRight)
  val maxPassThroughSum = maxOf(leftSum, rightSum).coerceAtLeast(0) + value
  return maxPassThroughSum to curMaxSum
}
