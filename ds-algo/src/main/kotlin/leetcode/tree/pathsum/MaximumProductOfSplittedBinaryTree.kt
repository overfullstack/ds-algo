package leetcode.tree.pathsum

import ds.tree.TreeNode

/* 08 Aug 2025 16:57 */

fun maxProduct(root: TreeNode?): Int {
  val totalSum = root.totalSum()
  return root.totalMaxProduct(totalSum).second.mod((1e9 + 7).toInt()) // ! Large test cases
}

fun TreeNode?.totalSum(): Long =
  when {
    this == null -> 0
    else -> `val` + left.totalSum() + right.totalSum()
  }

fun TreeNode?.totalMaxProduct(totalSum: Long): Pair<Long, Long> =
  when {
    this == null -> 0L to 0L
    else -> {
      val (leftSum, leftMaxProduct) = left.totalMaxProduct(totalSum)
      val (rightSum, rightMaxProduct) = right.totalMaxProduct(totalSum)
      val subtreeSum = `val` + leftSum + rightSum
      val maxProduct = maxOf(leftMaxProduct, rightMaxProduct, (totalSum - subtreeSum) * subtreeSum)
      subtreeSum to maxProduct
    }
  }

fun main() {
  println(maxProduct(TreeNode.levelOrderToIncompleteTree(listOf(1, 2, 3, 4, 5, 6))))
  println(
    maxProduct(TreeNode.levelOrderToIncompleteTree(listOf(1, null, 2, 3, 4, null, null, 5, 6)))
  )
}
