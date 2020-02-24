/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.TreeNode

private fun pathSum(root: TreeNode?, sum: Int): Int {
    return root.pathCountForSum(sum, mutableMapOf(), 0)
}

private fun TreeNode?.pathCountForSum(
    targetSum: Int,
    sumToPathCount: MutableMap<Int, Int>,
    runningSum: Int
): Int {
    val curSum = runningSum + (this?.`val` ?: return 0)
    var totalPathCount = sumToPathCount.getOrDefault(curSum - targetSum, 0) + if (curSum == targetSum) 1 else 0

    sumToPathCount.merge(curSum, 0) { pathCount, _ -> pathCount.inc() }
    totalPathCount += (left.pathCountForSum(targetSum, sumToPathCount, curSum)
            + right.pathCountForSum(targetSum, sumToPathCount, curSum)) // This is like fold, for left n right
    sumToPathCount.computeIfPresent(curSum) { _, pathCount -> if (pathCount > 0) pathCount.dec() else 0 }

    return totalPathCount
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    println(pathSum(TreeNode.listToCompleteTree(arr), targetSum))
}
