package cci.trees

import ds.tree.TreeNode

fun TreeNode.firstCommonAncestor(val1: Int, val2: Int): TreeNode? {
    if (value == val1 || value == val2) {
        return this
    }
    val findOnLeft = left?.firstCommonAncestor(val1, val2)
    if (findOnLeft != null && findOnLeft.value != val1 && findOnLeft.value != val2) {
        return findOnLeft
    }
    val findOnRight = right?.firstCommonAncestor(val1, val2)
    if (findOnRight != null && findOnRight.value != val1 && findOnRight.value != val2) {
        return findOnRight
    }

    if (findOnLeft != null && findOnRight != null) {
        return this
    }

    return if (findOnLeft != null) findOnLeft else findOnRight
}
