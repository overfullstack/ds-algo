package algoexpert.tree

import ga.overfullstack.ds.tree.TreeNode
import kotlin.math.abs

fun TreeNode.closestNodeInBST(valueToSearch: Int, closest: Int = value): Int {
  if (valueToSearch == value) {
    return value
  }
  var newClosest = closest
  if (abs(value - valueToSearch) < abs(closest - valueToSearch)) {
    newClosest = value
  }
  return when {
    valueToSearch < value -> left?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    valueToSearch > value -> right?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    else -> newClosest
  }
}
