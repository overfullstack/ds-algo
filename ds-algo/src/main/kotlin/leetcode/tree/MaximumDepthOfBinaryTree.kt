package leetcode.tree

import ds.tree.TreeNode

/* 22 Aug 2025 11:14 */

/**
 * [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
 */
fun maxDepth(root: TreeNode?): Int {
  if (root == null) {
    return 0
  }
  return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
}
