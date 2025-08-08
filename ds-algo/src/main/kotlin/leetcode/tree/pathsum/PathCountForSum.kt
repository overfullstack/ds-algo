/* gakshintala created on 12/13/19 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

/**
 * https://leetcode.com/problems/path-sum-iii/ Find the number of paths that sum to a given value.
 */
fun TreeNode?.pathCountForSum(
  targetSum: Int,
  sumToPathCount: MutableMap<Long, Int> = mutableMapOf(),
  runningSum: Long = 0,
): Int {
  if (this == null) {
    return 0
  }
  val curSum = runningSum + this.`val`
  // ! Increment the no.of times this sum has occurred
  sumToPathCount.merge(curSum, 1, Int::plus)
  // * PathCount from parent
  var totalPathCount =
    (sumToPathCount[curSum - targetSum] ?: 0) + if (curSum == targetSum.toLong()) 1 else 0
  // * PathCount from left and right
  totalPathCount +=
    (left.pathCountForSum(targetSum, sumToPathCount, curSum) +
      right.pathCountForSum(targetSum, sumToPathCount, curSum))
  // ! As we backtrack, remove current node contribution to sum
  sumToPathCount.computeIfPresent(curSum) { _, pathCount ->
    if (pathCount >= 2) pathCount.dec() else null
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
    TreeNode.levelOrderToIncompleteTree(listOf(1, -2, -3, 1, 3, -2, null, -1)).pathCountForSum(3)
  ) // 1
}
