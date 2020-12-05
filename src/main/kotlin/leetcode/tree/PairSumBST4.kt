/* gakshintala created on 1/18/20 */
package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.isPairWithSumPresent(targetSum: Int): Boolean {
    val smallStk = ArrayDeque<TreeNode>()
    val bigStk = ArrayDeque<TreeNode>()

    this.addLeftMost(smallStk)
    this.addRightMost(bigStk)

    while (smallStk.first().`val` < bigStk.first().`val`) { // * Loop till they cross each other.
        val curSum = smallStk.first().`val` + bigStk.first().`val`
        when {
            // * Next in inorder, or next smallest number. If no `right`, `pop()` takes care of exposing next smallest number.
            curSum < targetSum -> smallStk.removeFirst().right?.addLeftMost(smallStk)
            curSum > targetSum -> bigStk.removeLast().left?.addRightMost(bigStk) // Next in reverse inorder, or next greater number.
            else -> return true
        }
    }
    return false
}

private tailrec fun TreeNode.addLeftMost(smallStk: ArrayDeque<TreeNode>) {
    smallStk.add(this)
    left?.addLeftMost(smallStk)
}

private tailrec fun TreeNode.addRightMost(bigStk: ArrayDeque<TreeNode>) {
    bigStk.add(this)
    right?.addRightMost(bigStk)
}
