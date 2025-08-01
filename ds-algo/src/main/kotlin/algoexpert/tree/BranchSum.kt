package algoexpert.tree

import ds.tree.TreeNode

fun TreeNode.branchSum(curBranchSum: Int = 0): List<Int> {
  val newBranchSum = curBranchSum + `val`
  return left?.branchSum(newBranchSum)?.plus(right?.branchSum(newBranchSum) ?: emptyList())
    ?: listOf(newBranchSum)
}
