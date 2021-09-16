/* gakshintala created on 1/19/20 */
package leetcode.tree

import ds.tree.TreeNode

private fun TreeNode.allPathsFromRootToLeaf(path: String = ""): List<String> =
  if (left == null && right == null) {
    listOf(path + `val`)
  } else {
    (
      (left?.allPathsFromRootToLeaf(path + `val`) ?: emptyList()) +
        (right?.allPathsFromRootToLeaf(path + `val`) ?: emptyList())
      )
  }

fun TreeNode.sumFromRootToLeaf() = allPathsFromRootToLeaf().sumOf { it.toInt() }
