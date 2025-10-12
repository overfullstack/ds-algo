/* gakshintala created on 1/18/20 */
package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.isPairWithSumPresent(targetSum: Int): Boolean {
  val smallStk = ArrayDeque<TreeNode>()
  val bigStk = ArrayDeque<TreeNode>()

  this.addLeftmost(smallStk)
  this.addRightmost(bigStk)

  while (smallStk.first().`val` < bigStk.first().`val`) { // * Loop till they cross each other.
    val curSum = smallStk.first().`val` + bigStk.first().`val`
    when {
      // * Next in inorder, or next smallest number. If no `right`, `pop()` takes care of exposing
      // next smallest number.
      curSum < targetSum -> smallStk.removeFirst().right?.addLeftmost(smallStk)
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
