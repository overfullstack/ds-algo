package leetcode.tree

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * * This is for In-Complete tree
 */
fun TreeNode.connect2(): TreeNode {
    left?.next = right ?: findNext()
    right?.next = findNext()

    right?.connect2() // ! right is traversed before left
    left?.connect2()

    return this
}

private tailrec fun TreeNode.findNext(): TreeNode? = next?.left ?: next?.right ?: next?.findNext()