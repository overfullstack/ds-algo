/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.tree.TreeNode

private fun TreeNode.allPathsTopToBottomForSum(
    sum: Int,
    result: List<Int> = emptyList()
): List<List<Int>> {
    if (left == null && right == null) {
        return if (sum - `val` == 0) listOf(result + `val`) else emptyList()
    }
    // emptyList() signifies, if you step on a null before reaching the sum,
    // all that you have accumulated in this recursion path is useless, go to other direction or parent recursive call.
    return (
        (left?.allPathsTopToBottomForSum(sum - `val`, result + `val`) ?: emptyList()) +
            (right?.allPathsTopToBottomForSum(sum - `val`, result + `val`) ?: emptyList())
        )
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }
            .map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    TreeNode.levelOrderToTree(arr)?.allPathsTopToBottomForSum(targetSum)?.forEach(::println)
}
