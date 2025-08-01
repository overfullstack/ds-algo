package algoexpert.tree

import ds.tree.TreeNode

fun getOperator(symbol: Int): (Int, Int) -> Int =
  when (symbol) {
    -1 -> Int::plus
    -2 -> Int::minus
    -3 -> Int::div
    -4 -> Int::times
    else -> { _, _ -> symbol }
  }

fun TreeNode.evaluateExpressionTree(): Int {
  val left = left?.evaluateExpressionTree() ?: 0
  val right = right?.evaluateExpressionTree() ?: 0
  return getOperator(`val`)(left, right)
}
