package leetcode.tree

import ds.tree.TreeNode

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
  when (root) {
      null -> return null
      p if p == q -> return root
  }

  val fromLeft = lowestCommonAncestor(root.left, p, q)
  // * Search on the other (right) side if:
  //    * None of `p` or `q` found.
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
    fromLeft != null && fromRight != null -> root // ! Ancestor found, as both are found in subtree
    root.`val` == p?.`val` || root.`val` == q?.`val` -> root // One of them found
    else -> fromLeft ?: fromRight // One or none found in subtree
  }
}

fun main() {
  println(
    lowestCommonAncestor(
      TreeNode.levelOrderToIncompleteTree(listOf(1, null, 2, null, 3))!!,
      TreeNode(2),
      TreeNode(3)
    ),
  )
}
