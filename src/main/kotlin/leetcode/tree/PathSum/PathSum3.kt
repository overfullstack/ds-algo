/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.TreeNode

private fun pathSum(root: TreeNode?, sum: Int): Int {
    return pathForSum(root, sum, mutableMapOf(), 0)
}

private fun pathForSum(
    root: TreeNode?,
    targetSum: Int,
    sumToPathCount: MutableMap<Int, Int>,
    runningSum: Int
): Int {
    if (root == null) {
        return 0
    }
    val curSum = runningSum + root.`val`
    var totalPathCount = sumToPathCount.getOrDefault(curSum - targetSum, 0) + if (curSum == targetSum) 1 else 0

    sumToPathCount.merge(curSum, 0) { pathCount, _ -> pathCount.inc() }
    totalPathCount += (pathForSum(root.left, targetSum, sumToPathCount, curSum)
            + pathForSum(root.right, targetSum, sumToPathCount, curSum)) // This is like fold, for left n right
    sumToPathCount.computeIfPresent(curSum) { _, pathCount -> if (pathCount > 0) pathCount.dec() else 0 }

    return totalPathCount
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    println(pathSum(TreeNode.readPreOrderListToTree(arr), targetSum))
}