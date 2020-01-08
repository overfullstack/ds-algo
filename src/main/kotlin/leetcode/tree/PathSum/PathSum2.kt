/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.TreeNode

private fun TreeNode?.pathForSum(sum: Int, result: List<Int> = emptyList()): List<List<Int>> {
    if (this == null) {
        return emptyList()
    }
    if (left == null && right == null) {
        return if (sum - `val` == 0) listOf(result + `val`) else emptyList()
    }
    return (left.pathForSum(sum - `val`, result + `val`)
            + right.pathForSum(sum - `val`, result + `val`))
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    TreeNode.readPreOrderListToTree(arr)?.pathForSum(targetSum)?.forEach(::println)
}