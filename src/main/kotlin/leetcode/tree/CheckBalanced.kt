package cci.trees

import ds.tree.TreeNode
import kotlin.math.abs

fun TreeNode?.checkBalanced(): Pair<Int, Boolean> =
    if (this == null) {
        Pair(0, true)
    } else {
        val (leftHeight, isLeftSubTreeBalanced) = left.checkBalanced()
        if (!isLeftSubTreeBalanced) {
            leftHeight to isLeftSubTreeBalanced
        }
        val (rightHeight, isRightSubTreeBalanced) = right.checkBalanced()
        if (!isRightSubTreeBalanced) {
            rightHeight to isRightSubTreeBalanced
        }
        val areBothSidesBalanced = isLeftSubTreeBalanced && isRightSubTreeBalanced && abs(leftHeight - rightHeight) <= 1
        if (areBothSidesBalanced) {
            Pair(maxOf(leftHeight, rightHeight) + 1, true)
        } else {
            Pair(Int.MIN_VALUE, false)
        }
    }
