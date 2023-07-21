package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode
import kotlin.math.abs

fun TreeNode?.checkBalanced(): Pair<Int, Boolean> =
  if (this == null) {
    0 to true
  } else {
    val (leftHeight, isLeftSubTreeBalanced) = left.checkBalanced()
    if (!isLeftSubTreeBalanced) {
      leftHeight to isLeftSubTreeBalanced
    }
    val (rightHeight, isRightSubTreeBalanced) = right.checkBalanced()
    if (!isRightSubTreeBalanced) {
      rightHeight to isRightSubTreeBalanced
    }
    val areBothSidesBalanced =
      isLeftSubTreeBalanced && isRightSubTreeBalanced && abs(leftHeight - rightHeight) <= 1
    if (areBothSidesBalanced) {
      maxOf(leftHeight, rightHeight) + 1 to true
    } else {
      Int.MIN_VALUE to false
    }
  }
