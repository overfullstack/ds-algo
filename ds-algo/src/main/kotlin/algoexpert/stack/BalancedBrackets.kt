package algoexpert.stack

import java.util.Stack

private val bracketsPair = mapOf(
  ')' to '(',
  '}' to '{',
  ']' to '['
)

private val validBrackets = setOf('(', ')', '{', '}', '[', ']')

fun areBracketsBalanced(bracketsStr: String): Boolean {
  val stk = Stack<Char>()
  for (b in bracketsStr) {
    if (validBrackets.contains(b) && bracketsPair.contains(b)) {
      if (stk.isNotEmpty()) {
        val openingBracket = stk.pop()
        if (openingBracket != bracketsPair[b]) {
          return false
        }
      } else {
        return false
      }
    } else {
      stk.push(b)
    }
  }
  return stk.isEmpty()
}
