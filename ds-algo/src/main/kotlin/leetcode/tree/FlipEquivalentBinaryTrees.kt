package leetcode.tree

import ds.tree.TreeNode

/* 10 Aug 2025 13:13 */

/**
 * [951. Flip Equivalent Binary Trees](https://leetcode.com/problems/flip-equivalent-binary-trees/)
 */
fun flipEquiv(root1: TreeNode?, root2: TreeNode?): Boolean =
  when {
    root1 == null || root2 == null -> root1?.`val` == root2?.`val`
    else ->
      root1.`val` == root2.`val` &&
        (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
          flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))
  }
