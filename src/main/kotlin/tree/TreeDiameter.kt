/* gakshintala created on 10/16/19 */
package tree

import ds.tree.TreeNode
import ds.tree.TreeNode.Utils.levelOrderToCompleteTree

fun diameterOfBinaryTree(root: TreeNode?): Int {
    return if (root == null) 0 else root.findMaxDiameter().second - 1 // n nodes shall have n-1 edges.
}

fun TreeNode.findMaxDiameter(): Pair<Int, Int> { // Pair of height and diameter
    // Bottom-up approach, DFS traversal
    val (leftHeight, maxDiameterInLeft) = left?.findMaxDiameter() ?: 0 to 0
    val (rightHeight, maxDiameterInRight) = right?.findMaxDiameter() ?: 0 to 0

    val diameterViaRoot = leftHeight + rightHeight + 1
    val curMaxDiameter = maxOf(diameterViaRoot, maxDiameterInLeft, maxDiameterInRight)

    val curHeight = maxOf(leftHeight, rightHeight) + 1

    return curHeight to curMaxDiameter // curHeight is all we need to pass next, curMaxDiameter is just a max tracking variable.
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }
    println(diameterOfBinaryTree(levelOrderToCompleteTree(arr)))
}
