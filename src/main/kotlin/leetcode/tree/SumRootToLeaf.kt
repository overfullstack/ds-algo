/* gakshintala created on 1/19/20 */
package leetcode.tree

import ds.TreeNode

private fun TreeNode.allPathsFromRootToLeaf(path: String = ""): List<String> {
    if (left == null && right == null) {
        return listOf(path + `val`)
    }

    return ((left?.allPathsFromRootToLeaf(path + `val`) ?: emptyList())
            + (right?.allPathsFromRootToLeaf(path + `val`) ?: emptyList()))
}

fun TreeNode.sumFromRootToLeaf() = allPathsFromRootToLeaf().map { it.toInt() }.sum()
