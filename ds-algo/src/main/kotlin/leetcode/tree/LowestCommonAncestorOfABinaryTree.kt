package leetcode.tree

import ds.tree.TreeNode

/**
 * [236. Lowest Common Ancestor of a Binary
 * Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
 */
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
  when (root) {
    null -> return null
    p if p == q -> return root
  }

  val fromLeft = lowestCommonAncestor(root.left, p, q)
  // * Search on the other (right) side if:
  //    * None of `p` or `q` found, we get `null`
  //    * Only `p` or `q` found.
  // * If Ancestor found, bubble-up
  val isAncestorFoundOnLeft = fromLeft != null && fromLeft != p && fromLeft != q
  if (isAncestorFoundOnLeft) {
    return fromLeft
  }

  val fromRight = lowestCommonAncestor(root.right, p, q)
  val isAncestorFoundOnRight = fromRight != null && fromRight != p && fromRight != q
  if (isAncestorFoundOnRight) {
    return fromRight
  }

  return when {
    // ! Ancestor found, as both are found in subtree.
    // ! If `q` is in the subtree of `p`, `p` becomes and common ancestor, vice versa.
    fromLeft != null && fromRight != null -> root
    // ! We go till bottom even if `p` or `q` is found
    root.`val` == p?.`val` || root.`val` == q?.`val` -> root // This node is either p or q
    else -> fromLeft ?: fromRight // One or none found in subtree
  }
}

fun main() {
  println(
    lowestCommonAncestor(
        TreeNode.levelOrderToIncompleteTree(listOf(1, null, 2, null, 3))!!,
        TreeNode(2),
        TreeNode(3),
      )
      ?.`val`
  ) // 2
}
