/* gakshintala created on 5/26/20 */
package leetcode.tree

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
fun TreeNode.flatten(terminal: TreeNode? = null): TreeNode? {
  // * root -> left -> right
  // root coordinates linking left -> right, it passes terminal from right to left
  val terminalForLeft = right?.flatten(terminal) ?: terminal
  val terminalForRoot = left?.flatten(terminalForLeft) ?: terminalForLeft
  right = terminalForRoot // using `right` as slot for LL `next` (Problem specific)
  left = null // Dummy out left
  return this
}
