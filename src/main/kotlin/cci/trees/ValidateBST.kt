package cci.trees

import ds.tree.TreeNode

fun TreeNode?.validateBST(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean =
    if (this == null) {
        true
    } else {
        val isValValid = `val` >= min && `val` < max
        isValValid && left.validateBST(min, `val`) && right.validateBST(`val`, max)
    }
