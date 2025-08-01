package educative.tree

import ds.tree.TreeNode
import java.util.LinkedList

/* 19 Jul 2025 16:30 */

fun TreeNode.isSymmetric(): Boolean {
  val queue = LinkedList<TreeNode?>(listOf(left, right))
  while (queue.isNotEmpty()) {
    val left = queue.poll()
    val right = queue.poll()
    when {
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
