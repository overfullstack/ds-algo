package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.rightSideView(result: MutableList<Int> = mutableListOf(), depth: Int = 0): List<Int> {
    if (result.size == depth) {
        result.add(`val`)
    }
    right?.rightSideView(result, depth + 1)
    left?.rightSideView(result, depth + 1)

    return result
}
