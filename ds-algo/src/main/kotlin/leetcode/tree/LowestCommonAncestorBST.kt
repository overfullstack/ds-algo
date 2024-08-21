package leetcode.tree

import ds.tree.TreeNode

/** * This is for BST */
fun TreeNode.lowestCommonAncestorBST(p: TreeNode, q: TreeNode): TreeNode? =
  when {
    value > p.value && value > q.value -> left?.lowestCommonAncestor(p, q)
    value < p.value && value < q.value -> right?.lowestCommonAncestor(p, q)
    else -> this
  }
