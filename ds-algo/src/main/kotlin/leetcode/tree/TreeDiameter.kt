/* gakshintala created on 10/16/19 */
package leetcode.tree

import ds.tree.TreeNode
import ds.tree.TreeNode.Companion.levelOrderToCompleteTree

/** [1245 - Tree Diameter](https://leetcode.ca/2019-04-28-1245-Tree-Diameter/) */
fun diameterOfBinaryTree(root: TreeNode?): Int {
  return if (root == null) 0 else root.findMaxDiameter().second - 1 // n nodes shall have n-1 edges.
}

/**
 * Three Cases:
 * - Max diameter passes through current root
 * - Max diameter already found in left/right
 * - Max diameter will be found in parent nodes, with max of left pass or right pass
 */
fun TreeNode.findMaxDiameter(): Pair<Int, Int> { // Pair of height and diameter
  val (leftPassThroughHeight, maxDiameterInLeft) = left?.findMaxDiameter() ?: (0 to 0)
  val (rightPassThroughHeight, maxDiameterInRight) = right?.findMaxDiameter() ?: (0 to 0)
  val diameterWithRoot = leftPassThroughHeight + rightPassThroughHeight + 1
  val maxDiameter = maxOf(diameterWithRoot, maxDiameterInLeft, maxDiameterInRight)
  val passThroughDiameter = maxOf(leftPassThroughHeight, rightPassThroughHeight) + 1
  return passThroughDiameter to maxDiameter
}

fun main() {
  val arr = readln().split(",").map { it.trim().toInt() }
  println(diameterOfBinaryTree(levelOrderToCompleteTree(arr)))
}
