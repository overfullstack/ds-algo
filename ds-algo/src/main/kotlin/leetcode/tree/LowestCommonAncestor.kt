package leetcode.tree

import ds.tree.TreeNode

fun TreeNode?.lowestCommonAncestor(p: TreeNode, q: TreeNode): TreeNode? {
  when {
    this == null -> return null
    this == p && p == q -> return this
  }

  val left = left?.lowestCommonAncestor(p, q)
  // * Search on the other (right) side if:
  //    * None of `p` or `q` found.
  //    * Only `p` or `q` found.
  // * If Ancestor found, bubble-up
  val isAncestorFoundOnLeft = left != null && left != p && left != q
  if (isAncestorFoundOnLeft) {
    return left
  }

  val right = right?.lowestCommonAncestor(p, q)
  val isAncestorFoundOnRight = right != null && right != p && right != q
  if (isAncestorFoundOnRight) {
    return right
  }

  return when {
    this == p || this == q -> this // One of them found
    left != null && right != null -> this // ! Ancestor found, as both are found in subtree
    else -> left ?: right // One or none found in subtree
  }
}
