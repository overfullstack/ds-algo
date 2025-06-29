/* gakshintala created on 2/2/20 */
package gfg

import ds.tree.TreeNode

fun main() {
  val testCases = readln().toInt()
  repeat(testCases) {
    readln().toInt() // size
    val parentArr = readln().split(" ").map { it.toInt() }
    parentArr.indices.forEach { constructTree(it, parentArr) }
  }
}

fun constructTree(
  index: Int,
  parentArr: List<Int>,
  constructedNodes: Array<TreeNode?> = emptyArray(),
) {
  when {
    constructedNodes[index] != null || parentArr[index] == -1 -> return
    constructedNodes[parentArr[index]] == null ->
      constructTree(parentArr[index], parentArr, constructedNodes)
    else ->
      constructedNodes[parentArr[index]]?.run {
        if (left == null) {
          left = TreeNode(index)
        } else {
          right = TreeNode(index)
        }
      }
  }
}
