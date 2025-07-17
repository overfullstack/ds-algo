package educative.stack

import java.util.Stack

/* 25 Aug 2024 13:34 */
private val PAREN_MATCH = mapOf(')' to '(', '}' to '{', ']' to '[')
private val OPEN_PAREN = setOf('(', '{', '[')

fun validParentheses(str: String): Boolean {
  val stk = Stack<Char>()
  for (ch in str) {
    when {
      ch in OPEN_PAREN -> stk.push(ch)
      stk.isEmpty() || stk.pop() != PAREN_MATCH[ch] -> return false
    }
  }
  return stk.isEmpty()
}
