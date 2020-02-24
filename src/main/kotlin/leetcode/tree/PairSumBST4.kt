/* gakshintala created on 1/18/20 */
package leetcode.tree

import ds.TreeNode
import java.util.*

fun TreeNode.isPairWithSumPresent(targetSum: Int): Boolean {
    val smallStk = ArrayDeque<TreeNode>()
    val bigStk = ArrayDeque<TreeNode>()

    this.addLeftMost(smallStk)
    this.addRightMost(bigStk)

    while (smallStk.peek().`val` < bigStk.peek().`val`) { // Loop till they cross each other. 
        val curSum = smallStk.peek().`val` + bigStk.peek().`val`
        when {
            curSum < targetSum -> smallStk.pop().right?.addLeftMost(smallStk) // Next in inorder, or next smallest number.
            curSum > targetSum -> bigStk.pop().left?.addRightMost(bigStk) // Next in reverse inorder, or next greater number.
            else -> return true
        }
    }
    return false
}

private tailrec fun TreeNode.addLeftMost(smallStk: ArrayDeque<TreeNode>) {
    smallStk.push(this)
    left?.addLeftMost(smallStk)
}

private tailrec fun TreeNode.addRightMost(bigStk: ArrayDeque<TreeNode>) {
    bigStk.push(this)
    right?.addRightMost(bigStk)
}
