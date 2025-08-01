package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.kthSmallest(k: Int): Int =
  when {
    k == 0 -> `val`
    else -> kthSmallest(false to k).second
  }

private fun TreeNode.kthSmallest(result: Pair<Boolean, Int>): Pair<Boolean, Int> {
  val (foundOnLeft, curK) = left?.kthSmallest(result) ?: result
  // * Search left first and if not found, search right
  return when {
    !foundOnLeft ->
      when {
        curK == 1 -> true to `val` // ! reusing `curK` for bubbling back result
        else -> {
          val next = false to curK - 1 // -1 for current node
          right?.kthSmallest(next) ?: next
        }
      }
    else -> true to curK // curK here holds the result
  }
}

fun main() {
  println(
    TreeNode.levelOrderToIncompleteTree(listOf(5, 3, 6, 2, 4, null, null, 1))!!.kthSmallest(3)
  )
}
