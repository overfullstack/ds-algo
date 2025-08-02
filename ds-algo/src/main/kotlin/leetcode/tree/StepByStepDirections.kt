package leetcode.tree

import ds.tree.TreeNode

/* 01 Aug 2025 18:16 */

fun getDirections(root: TreeNode?, startValue: Int, destValue: Int): String {
  if (root == null || (root.`val` == startValue && startValue == destValue)) {
    return ""
  }
  val s = StringBuilder()
  val d = StringBuilder()
  root.findPath(startValue, s)
  root.findPath(destValue, d)
  val commonPrefixLength =
    (1..minOf(s.length, d.length)).takeWhile { s[s.length - it] == d[d.length - it] }.size
  return "U".repeat(s.length - commonPrefixLength) + d.reversed().substring(commonPrefixLength)
}

fun TreeNode.findPath(value: Int, path: StringBuilder): Boolean =
  when {
    value == `val` -> true
    else -> {
      when {
        left?.findPath(value, path) == true -> path.append('L')
        right?.findPath(value, path) == true -> path.append('R')
      }
      path.isNotEmpty()
    }
  }

fun TreeNode.getDirections(startValue: Int, destValue: Int, path: String = ""): Pair<Int?, String> {
  val (fromLeft, directionsFromLeft) =
    left?.getDirections(startValue, destValue, path + 'L') ?: (null to "")
  if (fromLeft != null && fromLeft != startValue && fromLeft != destValue) {
    return (fromLeft to directionsFromLeft)
  }

  val (fromRight, directionsFromRight) =
    right?.getDirections(startValue, destValue, path + 'R') ?: (null to "")
  if (fromRight != null && fromRight != startValue && fromRight != destValue) {
    return (fromRight to directionsFromRight)
  }

  return when {
    fromLeft != null && fromRight != null -> {
      val (first, second) =
        if (fromLeft == destValue) directionsFromRight to directionsFromLeft
        else directionsFromLeft to directionsFromRight
      val commonPrefixLength = first.zip(second).takeWhile { (a, b) -> a == b }.size
      this.`val` to
        ("U".repeat(first.length - commonPrefixLength) + second.substring(commonPrefixLength))
    }
    this.`val` == startValue ->
      startValue to
        when {
          fromLeft == destValue -> directionsFromLeft.substring(path.length)
          fromRight == destValue -> directionsFromRight.substring(path.length)
          else -> path
        }
    this.`val` == destValue ->
      destValue to
        when {
          fromLeft == startValue -> "U".repeat(directionsFromLeft.length - path.length)
          fromRight == startValue -> "U".repeat(directionsFromRight.length - path.length)
          else -> path
        }
    else -> fromLeft?.let { (fromLeft to directionsFromLeft) } ?: (fromRight to directionsFromRight)
  }
}

fun main() {
  println(
    getDirections(TreeNode.levelOrderToIncompleteTree(listOf(5, 1, 2, 3, null, 6, 4))!!, 3, 6)
  )
  // println(TreeNode.levelOrderToIncompleteTree(listOf(1,null,10,12,13,4,6,null,15,null,null,5,11,null,2,14,7,null,8,null,null,null,9,3))!!.getDirections(6, 15).second)
  // println(TreeNode.levelOrderToIncompleteTree(listOf(5,8,3,1,null,4,7,6,null,null,null,null,null,null,2))!!.getDirections(4, 3).second)
}
