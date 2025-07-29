package cci.trees

import ds.tree.TreeNode

fun TreeNode?.validateBST(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean =
  if (this == null) {
    true
  } else {
    val isValValid = value in min until max
    isValValid && left.validateBST(min, value) && right.validateBST(value, max)
  }
