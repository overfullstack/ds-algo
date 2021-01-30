package leetcode.tree

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal
 */
fun TreeNode.levelOrderTraversal(): List<List<TreeNode>> {
    var currentLevel = listOf(this)
    val result = mutableListOf<List<TreeNode>>()

    while (currentLevel.isNotEmpty()) {
        result += currentLevel
        currentLevel = currentLevel.flatMap { listOfNotNull(it.left, it.right) }
    }
    return result
}
