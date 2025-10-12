package cci.trees

import ds.tree.TreeNode

fun TreeNode.successor(): TreeNode? =
  if (right != null) {
    right!!.leftmost()
  } else {
    var node = this
    var parent = node.parent
    while (parent != null && parent.left != node) {
      node = parent
      parent = node.parent
    }
    parent
  }

fun TreeNode.leftmost(): TreeNode = left?.leftmost() ?: this
