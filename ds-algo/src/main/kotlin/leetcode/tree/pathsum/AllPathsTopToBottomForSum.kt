/* gakshintala created on 12/13/19 */
package leetcode.tree.pathsum

import ds.tree.TreeNode

private fun TreeNode.allPathsTopToBottomForSum(
  sum: Int,
  result: List<Int> = emptyList(),
): List<List<Int>> {
  if (left == null && right == null) { // ! Path has to end at leaf as per problem
    return if (sum - `val` == 0) listOf(result + `val`) else emptyList()
  }
  // ! `emptyList()` signifies, if you step on a null before reaching the sum,
  // all that you have accumulated in this recursion path is useless, go to other direction or
  // parent recursive call.
  return ((left?.allPathsTopToBottomForSum(sum - `val`, result + `val`) ?: emptyList()) +
    (right?.allPathsTopToBottomForSum(sum - `val`, result + `val`) ?: emptyList()))
}

fun main() {
  val arrCsv = readln()
  val arr =
    if (arrCsv.trim().isEmpty()) emptyList()
    else arrCsv.split(",").map { it.trim() }.map { if (it == "null") null else it.toInt() }
  val targetSum = readln().toInt()
  TreeNode.levelOrderToIncompleteTree(arr)?.allPathsTopToBottomForSum(targetSum)?.forEach(::println)
}
