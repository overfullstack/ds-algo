/* gakshintala created on 12/13/19 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

/** [437. Path Sum III](https://leetcode.com/problems/path-sum-iii/) */
fun pathSum(
  root: TreeNode?,
  targetSum: Int,
  sumToPathCount: MutableMap<Long, Int> = mutableMapOf(0L to 1), // ! For first sum occurrence
  runningSum: Long = 0,
): Int {
  if (root == null) {
    return 0
  }
  val curSum = runningSum + root.`val`
  val pathCountFromParent = sumToPathCount[curSum - targetSum] ?: 0
  // ! Increment the no.of times this sum has occurred
  sumToPathCount.merge(curSum, 1, Int::plus)
  val totalPathCount =
    pathCountFromParent +
      (pathSum(root.left, targetSum, sumToPathCount, curSum) +
        pathSum(root.right, targetSum, sumToPathCount, curSum))
  // ! As we backtrack, remove current node contribution to sum, as this is a shared global map
  sumToPathCount.computeIfPresent(curSum) { _, pathCount ->
    if (pathCount == 1) null else pathCount.dec()
  }
  return totalPathCount
}

fun main() {
  /*  val arrCsv = readln()
  val arr =
    if (arrCsv.trim().isEmpty()) emptyList()
    else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
  val targetSum = readln().toInt()*/
  println(
    pathSum(TreeNode.levelOrderToIncompleteTree(listOf(1, -2, -3, 1, 3, -2, null, -1)), 3)
  ) // 1
  println(pathSum(TreeNode.levelOrderToIncompleteTree(listOf(1)), 0)) // 0
}
