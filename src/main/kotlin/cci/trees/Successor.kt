package cci.trees

import ds.tree.TreeNode

fun TreeNode.successor(): TreeNode? =
    if (right != null) {
        right!!.leftMost()
    } else {
        var node = this
        var parent = node.parent
        while (parent != null && parent.left != node) {
            node = parent
            parent = node.parent
        }
        parent
    }

fun TreeNode.leftMost(): TreeNode = left?.leftMost() ?: this
