/* gakshintala created on 10/16/19 */
package tree

import ds.TreeNode
import ds.readArrToTree

fun diameterOfBinaryTree(root: TreeNode?): Int {
    return if (root == null) 0 else findMaxDiameter(root).second - 1 // -1 for counting edges between nodes
}

fun findMaxDiameter(root: TreeNode?): Pair<Int, Int> {
    if (root == null) {
        return Pair(0, 0)
    }
    val left = findMaxDiameter(root.left)
    val right = findMaxDiameter(root.right)

    val curHeight = maxOf(left.first, right.first) + 1
    val diameterThroughRoot = left.first + right.first + 1
    val curMaxDiameter = maxOf(diameterThroughRoot, left.second, right.second)
    return Pair(curHeight, curMaxDiameter)
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }
    println(diameterOfBinaryTree(readArrToTree(arr)))
}