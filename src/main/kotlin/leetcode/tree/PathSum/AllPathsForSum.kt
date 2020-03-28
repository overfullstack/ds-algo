/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.TreeNode

private fun TreeNode.allPathsForSum(sum: Int, result: List<Int> = emptyList()): List<List<Int>> {
    if (left == null && right == null) {
        return if (sum - `val` == 0) listOf(result + `val`) else emptyList()
    }
    // emptyList() signifies, if you step on a null, all that you have accumulated in this recursion path is useless, go to other direction or parent recursive call.
    return ((left?.allPathsForSum(sum - `val`, result + `val`) ?: emptyList())
            + (right?.allPathsForSum(sum - `val`, result + `val`) ?: emptyList()))
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }
            .map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    TreeNode.listToCompleteTree(arr)?.allPathsForSum(targetSum)?.forEach(::println)
}
