/* gakshintala created on 1/19/20 */
package leetcode.tree

import ds.tree.TreeNode

private fun TreeNode.allPathsFromRootToLeaf(path: String = ""): List<String> {
    if (left == null && right == null) {
        return listOf(path + value)
    }

    return ((left?.allPathsFromRootToLeaf(path + value) ?: emptyList())
            + (right?.allPathsFromRootToLeaf(path + value) ?: emptyList()))
}

fun TreeNode.sumFromRootToLeaf() = allPathsFromRootToLeaf().map { it.toInt() }.sum()
