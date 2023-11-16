package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode

fun TreeNode.deleteNode(key: Int): TreeNode? {
  when {
    key < value -> left = left?.deleteNode(key)
    key > value -> right = right?.deleteNode(key)
    else -> {
      when {
        left == null -> return right
        right == null -> return left
        // * Replace with min on right side, so it satifies the contract
        // * as all nodes on right must be greater
        else -> {
          value = right!!.findMin().value
          right = right!!.deleteNode(value) // ! delete the min
        }
      }
    }
  }
  return this
}

private tailrec fun TreeNode.findMin(): TreeNode = if (left == null) this else left!!.findMin()
