/* gakshintala created on 12/13/19 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

private fun TreeNode.allPathsTopToBottomForSum(
  sum: Int,
  result: List<Int> = emptyList(),
): List<List<Int>> {
  if (left == null && right == null) {
    return if (sum - value == 0) listOf(result + value) else emptyList()
  }
  // emptyList() signifies, if you step on a null before reaching the sum,
  // all that you have accumulated in this recursion path is useless, go to other direction or
  // parent recursive call.
  return ((left?.allPathsTopToBottomForSum(sum - value, result + value) ?: emptyList()) +
    (right?.allPathsTopToBottomForSum(sum - value, result + value) ?: emptyList()))
}

fun main() {
  val arrCsv = readln()
  val arr =
    if (arrCsv.trim().isEmpty()) emptyList()
    else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
  val targetSum = readln().toInt()
  TreeNode.levelOrderToTree(arr)?.allPathsTopToBottomForSum(targetSum)?.forEach(::println)
}
