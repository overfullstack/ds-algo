/* gakshintala created on 1/18/20 */
package leetcode.tree.PathSum

import ds.TreeNode

fun TreeNode.maxPathSum(): Pair<Int, Int> {
    val (leftSum, maxSumInLeft) = left?.maxPathSum() ?: 0 to Int.MIN_VALUE
    val (rightSum, maxSumInRight) = right?.maxPathSum() ?: 0 to Int.MIN_VALUE
    val sumViaRoot = maxOf(0, leftSum) + maxOf(
        0,
        rightSum
    ) + `val` // maxOf(0,...) is to avoid anything that lessens the sum, any negative numbers

    // pick from left or right, compare current sum with left/right, no need to add current val and compare because that is done in next recursion
    return (maxOf(0, leftSum, rightSum) + `val`) to maxOf(sumViaRoot, maxSumInLeft, maxSumInRight)
}