/* gakshintala created on 1/18/20 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

/** [binary-tree-maximum-path-sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) */
fun TreeNode.maxPathSum(): Pair<Int, Int> {
  val (leftPassThroughSum, maxSumInLeft) = left?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  val (rightPassThroughSum, maxSumInRight) = right?.maxPathSum() ?: (0 to Int.MIN_VALUE)
  // * `coerceAtLeast(0)` is to avoid any negative number that lessens the sum
  // * We consider root, only if the path passes through left and right
  val sumWithRoot =
    leftPassThroughSum.coerceAtLeast(0) + rightPassThroughSum.coerceAtLeast(0) + value
  val curMaxSum = maxOf(sumWithRoot, maxSumInLeft, maxSumInRight)
  val maxPassThroughSum = maxOf(leftPassThroughSum, rightPassThroughSum).coerceAtLeast(0) + value
  return maxPassThroughSum to curMaxSum
}
