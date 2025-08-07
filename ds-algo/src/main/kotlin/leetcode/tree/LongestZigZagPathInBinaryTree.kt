package leetcode.tree

import ds.tree.TreeNode

/* 06 Aug 2025 18:12 */

/**
 * [1372. Longest ZigZag Path in a Binary
 * Tree](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/)
 */
fun longestZigZag(root: TreeNode?): Int = root?.dfs()?.third ?: 0

fun TreeNode.dfs(): Triple<Int, Int, Int> {
  // ! Parent ignores rightRight and leftLeft as they don't form zigzag
  // ! `-1` for leaf as it takes minimum two levels to build a zigzag
  val (_, leftRightPath, leftMax) = left?.dfs() ?: Triple(-1, -1, Int.MIN_VALUE)
  val (rightLeftPath, _, rightMax) = right?.dfs() ?: Triple(-1, -1, Int.MIN_VALUE)

  val zigzagWithRoot = maxOf(leftRightPath, rightLeftPath) + 1
  val maxZigZag = maxOf(zigzagWithRoot, leftMax, rightMax)
  // * We send path coming from both left and right and let the parent decide
  // ! In `MaxPathSum`, we are sure parent needs `maxOf(leftSum, rightSum)` despite the direction
  // ! Unlike `MaxPathSum`, we are unsure what parent wants, as it can be on any side.
  // ! So we send both
  return Triple(leftRightPath + 1, rightLeftPath + 1, maxZigZag)
}
