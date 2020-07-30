package leetcode.tree

import ds.tree.TreeNode

fun TreeNode?.lowestCommonAncestor(p: TreeNode, q: TreeNode): TreeNode? {
    if (this == null || this == p || this == q) {
        return this
    }

    val left = left?.lowestCommonAncestor(p, q)
    if (left != null && left != p && left != q) {
        return left
    }

    val right = right?.lowestCommonAncestor(p, q)
    if (right != null && right != p && right != q) {
        return right
    }

    return if (left != null && right != null) {
        this
    } else {
        if (left == null) right else left
    }
}
