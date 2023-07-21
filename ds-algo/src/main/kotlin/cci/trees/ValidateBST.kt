package cci.trees

import ga.overfullstack.ds.tree.TreeNode

fun TreeNode?.validateBST(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean =
  if (this == null) {
    true
  } else {
    val isValValid = value >= min && value < max
    isValValid && left.validateBST(min, value) && right.validateBST(value, max)
  }
