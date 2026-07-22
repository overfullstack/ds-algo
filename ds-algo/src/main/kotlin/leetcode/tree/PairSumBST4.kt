/* gakshintala created on 1/18/20 */
package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.isPairWithSumPresent(targetSum: Int): Boolean {
  val smallStk = ArrayDeque<TreeNode>()
  val bigStk = ArrayDeque<TreeNode>()

  this.addLeftmost(smallStk)
  this.addRightmost(bigStk)

  while (smallStk.last().`val` < bigStk.last().`val`) { // * Loop till they cross each other.
    val curSum = smallStk.last().`val` + bigStk.last().`val`
    when {
      // * Next in inorder, or next smallest number. If no `right`, `removeLast()` takes care of
      // exposing next smallest number.
      curSum < targetSum -> smallStk.removeLast().right?.addLeftmost(smallStk)
      curSum > targetSum ->
        bigStk
          .removeLast()
          .left
          ?.addRightmost(bigStk) // Next in reverse inorder, or next greater number.
      else -> return true
    }
  }
  return false
}

private fun TreeNode.addLeftmost(smallStk: ArrayDeque<TreeNode>) {
  smallStk.add(this)
  left?.addLeftmost(smallStk)
}

private fun TreeNode.addRightmost(bigStk: ArrayDeque<TreeNode>) {
  bigStk.add(this)
  right?.addRightmost(bigStk)
}
