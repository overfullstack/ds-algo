/* gakshintala created on 8/9/19 */
package hackerrank.stack

import java.util.Scanner
import java.util.Stack

fun isBalanced(s: String): String {
  if (s.isEmpty()) {
    return "YES"
  }
  val stk = Stack<Char>()
  var result: Boolean
  for (c in s) {
    when (c) {
      '[', '{', '(' -> stk.push(c)
      ']', '}', ')' -> {
        if (stk.isEmpty()) {
          return "NO"
        }
        result = when (stk.pop()) {
          '[' -> c == ']'
          '{' -> c == '}'
          '(' -> c == ')'
          else -> false
        }
        if (!result) {
          return "NO"
        }
      }
      else -> return "NO"
    }
  }
  return if (stk.isEmpty()) "YES" else "NO" // ! Check stack status in the end.
}

fun main() {
  val scan = Scanner(System.`in`)

  val t = scan.nextLine().trim().toInt()

  for (tItr in 1..t) {
    val s = scan.nextLine()

    val result = isBalanced(s)

    println(result)
  }
}
