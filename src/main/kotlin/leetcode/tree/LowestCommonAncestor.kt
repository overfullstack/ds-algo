package leetcode.tree

import ds.tree.TreeNode

fun TreeNode?.lowestCommonAncestor(p: TreeNode, q: TreeNode): TreeNode? {
    if (this == null || this == p || this == q) {
        return this
    }

    val left = left?.lowestCommonAncestor(p, q)
    if (left != null && left != p && left != q) { // This is the result (common ancestor), just pass it up.
        return left
    }

    // If you crossed above if, p/q is on `this` left, search on the right for the other

    val right = right?.lowestCommonAncestor(p, q)
    if (right != null && right != p && right != q) {
        return right
    }

    return when { // return common ancestor or non-null or null
        left != null && right != null -> this
        left == null -> right
        right == null -> left
        else -> null
    }
}
