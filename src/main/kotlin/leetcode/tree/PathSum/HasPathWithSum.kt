/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.tree.TreeNode

private fun TreeNode.hasPathSum(sum: Int): Boolean =
    left?.hasPathSum(sum - value) ?: right?.hasPathSum(sum - value) ?: sum - value == 0


fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }
            .map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    println(TreeNode.levelOrderToCompleteTree(arr)?.hasPathSum(targetSum))
}
