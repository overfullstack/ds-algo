package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.kthSmallest(k: Int): Int =
  when {
    k == 0 -> value
    else -> kthSmallest(false to k).second
  }

private fun TreeNode.kthSmallest(result: Pair<Boolean, Int>): Pair<Boolean, Int> {
  val (foundOnLeft, curK) = left?.kthSmallest(result) ?: result
  return if (!foundOnLeft) {
    if (curK == 1) {
      true to value
    } else {
      val next = false to curK - 1 // -1 for current node
      right?.kthSmallest(next) ?: next
    }
  } else {
    true to curK // curK here is filled with `val` of kthSmallest
  }
}

fun main() {
  println(TreeNode.levelOrderToTree(listOf(5, 3, 6, 2, 4, null, null, 1))!!.kthSmallest(3))
}
