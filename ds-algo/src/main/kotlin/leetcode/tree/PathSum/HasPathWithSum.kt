/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ga.overfullstack.ds.tree.TreeNode

/** https://leetcode.com/problems/path-sum/ Sum from root-to-leaf */
private fun TreeNode?.hasPathSum(sum: Int): Boolean =
  when {
    this == null -> false
    left == null && right == null -> (sum - value) == 0
    else -> left.hasPathSum(sum - value) || right.hasPathSum(sum - value)
  }
