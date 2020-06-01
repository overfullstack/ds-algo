/* gakshintala created on 12/13/19 */
package leetcode.tree.PathSum

import ds.tree.TreeNode

private fun TreeNode?.pathCountForSum(
    targetSum: Int,
    sumToPathCount: MutableMap<Int, Int> = mutableMapOf(),
    runningSum: Int = 0
): Int {
    val curSum = runningSum + (this?.value ?: return 0)
    var totalPathCount = sumToPathCount.getOrDefault(curSum - targetSum, 0) + if (curSum == targetSum) 1 else 0

    sumToPathCount.merge(curSum, 1, Int::plus)
    totalPathCount += (left.pathCountForSum(targetSum, sumToPathCount, curSum)
            + right.pathCountForSum(targetSum, sumToPathCount, curSum)) // This is like fold, for left n right
    // Backtraking, removing the path contributed by this node, by decrementing the pathCount for curSum.
    // This makes this hashmap reusable for other paths.
    // We still have our `totalPathCount` safe for this path, which shall be returned up for accumulation.
    sumToPathCount.computeIfPresent(curSum) { _, pathCount -> if (pathCount > 1) pathCount.dec() else null }
    return totalPathCount
}

fun main() {
    val arrCsv = readLine()!!
    val arr =
        if (arrCsv.trim().isEmpty()) emptyList() else arrCsv.split(",").map { it.trim() }
            .map { if (it == "null") null else it.toInt() }
    val targetSum = readLine()!!.toInt()
    println(TreeNode.levelOrderToCompleteTree(arr).pathCountForSum(targetSum))
}
