package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode

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
