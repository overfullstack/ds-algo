package educative.tree

import ds.tree.TreeNode

/* 29 Jul 2025 08:27 */

fun buildTree(
  preOrder: IntArray,
  inOrder: IntArray,
  inLeft: Int = 0,
  inRight: Int = inOrder.lastIndex,
  preIndex: Int = 0,
): Pair<Int, TreeNode?> {
  when {
    preIndex > preOrder.lastIndex -> return (preIndex to null)
    // ! No `preIndex+1` as it shall be incremented while traversing left or right
    inLeft == inRight -> return (preIndex to TreeNode(preOrder[preIndex]))
  }

  val value = preOrder[preIndex]
  val inIndex = inOrder.findInIndex(value, inLeft, inRight)
  val (nextPreIndexFromLeft, leftNode) =
    buildTree(preOrder, inOrder, inLeft, inIndex - 1, preIndex + 1)
  val (nextPreIndexFromRight, rightNode) =
    buildTree(preOrder, inOrder, inIndex + 1, inRight, nextPreIndexFromLeft + 1)
  // ! No `nextPreIndexFromRight+1` as it shall be incremented while traversing left or right
  return nextPreIndexFromRight to TreeNode(value, leftNode, rightNode)
}

private fun IntArray.findInIndex(value: Int, inLeft: Int, inRight: Int): Int =
  (inLeft..inRight).first { this[it] == value }

fun main() {
  println(buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7)).second?.incompleteTreeToLevelOrderList())
}
