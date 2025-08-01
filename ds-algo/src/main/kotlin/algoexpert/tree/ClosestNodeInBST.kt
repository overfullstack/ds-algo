package algoexpert.tree

import ds.tree.TreeNode
import kotlin.math.abs

fun TreeNode.closestNodeInBST(valueToSearch: Int, closest: Int = `val`): Int {
  var newClosest = closest
  if (abs(`val` - valueToSearch) < abs(closest - valueToSearch)) { // Is cur value closer
    newClosest = `val`
  }
  return when {
    valueToSearch < `val` -> left?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    valueToSearch > `val` -> right?.closestNodeInBST(valueToSearch, newClosest) ?: newClosest
    else -> newClosest
  }
}
