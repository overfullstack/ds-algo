package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.deleteNode(key: Int): TreeNode? {
    when {
        key < `val` -> left = left?.deleteNode(key)
        key > `val` -> right = right?.deleteNode(key)
        else -> {
            when {
                left == null -> return right
                right == null -> return left
                else -> { // * Replace with min on right side, so it satifies the contract as all nodes on right must be greater
                    `val` = right!!.findMin().`val`
                    right = right!!.deleteNode(`val`) // ! delete the min
                }
            }
        }
    }
    return this
}

private tailrec fun TreeNode.findMin(): TreeNode = if (left == null) this else left!!.findMin()