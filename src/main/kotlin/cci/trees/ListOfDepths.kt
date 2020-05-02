package cci.trees

import ds.tree.TreeNode

fun TreeNode.listOfDepths(): List<List<TreeNode>> {
    var currentLevel = listOf(this)
    val result = mutableListOf<List<TreeNode>>()

    while (currentLevel.isNotEmpty()) {
        result.add(currentLevel)
        currentLevel = currentLevel.flatMap { listOf(it.left, it.right).filterNotNull() }
    }
    return result
}
