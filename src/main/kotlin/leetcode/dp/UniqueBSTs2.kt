package leetcode.dp

import ds.tree.TreeNode

fun generateTrees(n: Int): List<TreeNode?> = if (n == 0) emptyList() else generateTreesUtil(n)

private fun generateTreesUtil(n: Int, start: Int = 1, end: Int = n): List<TreeNode?> =
    if (start > end) {
        listOf(null)
    } else { // This is bottom-up of what we did in UniqueBSTs with dp (top-down)
        (start..end).flatMap { i ->
            val leftList = generateTreesUtil(n, start, i - 1)
            val rightList = generateTreesUtil(n, i + 1, end)
            leftList.flatMap { left -> rightList.map { right -> TreeNode(i, left, right) } }
        }
    }
