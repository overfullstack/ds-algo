package leetcode.tree

import ds.tree.TreeNode

fun TreeNode?.lowestCommonAncestor(p: TreeNode, q: TreeNode): TreeNode? {
  when {
    this == null -> return null
    this == p && this == q -> return this
  }

  val left = left?.lowestCommonAncestor(p, q)
  // It bubbles-up for 3 scenarios
  // * You found some unrelated node, so bubble-up from that area and search on the other-side of
  // it's parent
  // * You found the common ancestor, so quickly bubble-up in the route you came-in
  if (left != null && left != p && left != q) {
    return left
  }

  // If you crossed above if, p/q is on `this` left, search on the right for the other one

  val right = right?.lowestCommonAncestor(p, q)
  if (right != null && right != p && right != q) {
    return right
  }

  return when {
    left != null && right != null -> this
    else -> left ?: right
  }
}
