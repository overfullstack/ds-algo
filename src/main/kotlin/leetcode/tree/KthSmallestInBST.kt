package leetcode.tree

import ds.tree.TreeNode

fun TreeNode.kthSmallest(k: Int): Int =
    when {
        k == 0 -> `val`
        else -> kthSmallest(false to k).second
    }

private fun TreeNode.kthSmallest(result: Pair<Boolean, Int>): Pair<Boolean, Int> {
    val leftResult = left?.kthSmallest(result) ?: result
    return if (!leftResult.first) {
        if (leftResult.second == 1) {
            (true to `val`)
        } else {
            val next = false to leftResult.second - 1 // -1 for current node
            right?.kthSmallest(next) ?: next
        }
    } else {
        leftResult
    }
}

fun main() {
    println(TreeNode.levelOrderToTree(listOf(5, 3, 6, 2, 4, null, null, 1))!!.kthSmallest(3))
}
