package educative.stack

import java.util.Stack

/* 27 Aug 2024 09:33 */

fun removeAdjacentDuplicates(str: String): String {
  val stk = Stack<Char>()
  for (ch in str) {
    if (stk.isNotEmpty() && stk.peek() == ch) {
      stk.pop()
    } else {
      stk.push(ch)
    }
  }
  return stk.joinToString("")
}
