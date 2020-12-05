package leetcode.tree

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * * This is for balanced tree.
 */
fun TreeNode.connect(): TreeNode {
    left?.next = right
    right?.next = next?.left

    left?.connect()
    right?.connect()

    return this
}