package leetcode.tree

import ds.tree.TreeNode

/** * This is for BST */
fun TreeNode.lowestCommonAncestorBST(p: TreeNode, q: TreeNode): TreeNode? =
  when {
    value > p.value && value > q.value -> left?.lowestCommonAncestorBST(p, q)
    value < p.value && value < q.value -> right?.lowestCommonAncestorBST(p, q)
    else -> this
  }
