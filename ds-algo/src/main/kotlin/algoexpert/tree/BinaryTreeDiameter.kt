package algoexpert.tree

import ga.overfullstack.ds.tree.TreeNode

fun TreeNode.diameter(): Pair<Int, Int> {
  val (leftDiameter, maxDiameterFromLeft) = left?.diameter() ?: (0 to 0)
  val (rightDiameter, maxDiameterFromRight) = right?.diameter() ?: (0 to 0)
  // * No `+1` because this problem only counts the edges not nodes
  val maxDiameter = maxOf((leftDiameter + rightDiameter), maxDiameterFromLeft, maxDiameterFromRight)
  return (maxOf(leftDiameter, rightDiameter) + 1 to maxDiameter)
}
