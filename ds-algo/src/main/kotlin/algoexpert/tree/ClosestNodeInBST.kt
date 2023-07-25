package algoexpert.tree

import ga.overfullstack.ds.tree.TreeNode
import kotlin.math.abs

fun TreeNode.closestNodeInBST(valueToSearch: Int, closest: Int = value): Int {
  var newClosest = closest
  if (abs(value - valueToSearch) < abs(closest - valueToSearch)) { // Is cur value closer
    newClosest = value
  }
  return when {
    valueToSearch < value -> left?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    valueToSearch > value -> right?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    else -> newClosest
  }
}
