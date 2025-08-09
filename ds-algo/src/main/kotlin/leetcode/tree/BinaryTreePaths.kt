package leetcode.tree

import ds.tree.TreeNode

/* 09 Aug 2025 15:19 */

fun binaryTreePaths(root: TreeNode?): List<String> =
  binaryTreePaths3(root).map { it.joinToString("->") }

fun binaryTreePaths2(root: TreeNode?): List<List<Int>> =
  when {
    root == null -> emptyList()
    root.left == null && root.right == null -> listOf(listOf(root.`val`))
    else ->
      (binaryTreePaths2(root.left) + binaryTreePaths2(root.right)).map { listOf(root.`val`) + it }
  }

fun binaryTreePaths3(root: TreeNode?, path: List<Int> = emptyList()): List<List<Int>> =
  when {
    root == null -> emptyList()
    root.left == null && root.right == null -> listOf(path + root.`val`)
    else ->
      (binaryTreePaths3(root.left, path + root.`val`) +
        binaryTreePaths3(root.right, path + root.`val`))
  }

fun main() {
  println(
    binaryTreePaths(TreeNode.levelOrderToIncompleteTree(listOf(1, 2, 3, null, 5))!!)
  ) // output: ["1->2->5","1->3"]
}
