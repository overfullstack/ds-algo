package cci.trees

import ds.tree.TreeNode

fun arrToBST(arr: IntArray, low: Int = 0, high: Int = arr.lastIndex): TreeNode? =
  when {
    arr.isEmpty() -> null
    low == high -> TreeNode(arr[low])
    else -> {
      val mid = (low + high) / 2
      val treeNode = TreeNode(arr[mid])
      treeNode.left = arrToBST(arr, low, mid - 1)
      treeNode.right = arrToBST(arr, mid + 1, high)
      treeNode
    }
  }
