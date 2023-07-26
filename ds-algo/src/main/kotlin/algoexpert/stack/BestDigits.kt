package algoexpert.stack

import java.util.Stack

fun bestDigits(str: String, numDigits: Int): String {
  val stk = Stack<Char>()
  var n = numDigits
  for (d in str) {
    if (n != 0 && stk.isNotEmpty() && stk.peek() < d) {
      stk.pop()
      n--
    }
    stk.push(d)
  }
  return stk.joinToString("")
}
