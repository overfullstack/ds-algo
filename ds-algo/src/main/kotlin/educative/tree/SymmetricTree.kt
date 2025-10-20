package educative.tree

import ds.tree.TreeNode
import ds.tree.TreeNode.Companion.levelOrderToIncompleteTree
import java.util.*

/* 19 Jul 2025 16:30 */

/** [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) */
fun TreeNode.isSymmetric(): Boolean {
  val queue = LinkedList<TreeNode?>(listOf(left, right))
  while (queue.isNotEmpty()) {
    val left = queue.poll()
    val right = queue.poll()
    when {
      left == null && right == null -> continue // ! To flush-out nulls from queue
      left?.`val` != right?.`val` -> return false
      else -> {
        queue.add(left?.left)
        queue.add(right?.right)
        queue.add(left?.right)
        queue.add(right?.left)
      }
    }
  }
  return true
}

fun main() {
  val root = levelOrderToIncompleteTree(listOf(1, 2, 2, 3, 4, 4, 3))
  println(root?.isSymmetric()) // true
  val root2 = levelOrderToIncompleteTree(listOf(1, 2, 2, null, 3, null, 3))
  println(root2?.isSymmetric()) // false
}
