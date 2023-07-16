package leetcode.tree

import ds.tree.TreeNode

/** * This is for BST */
fun TreeNode.lowestCommonAncestorBST(p: TreeNode, q: TreeNode): TreeNode? =
  when {
    `val` > p.`val` && `val` > q.`val` -> left?.lowestCommonAncestor(p, q)
    `val` < p.`val` && `val` < q.`val` -> right?.lowestCommonAncestor(p, q)
    else -> this
  }
