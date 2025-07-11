/* gakshintala created on 1/23/20 */
package leetcode.backtracking

fun generateParenthesis(
  n: Int,
  left: Int = 0,
  right: Int = 0,
  parenStr: String = "",
): List<String> =
  when {
    left > n || right > left -> emptyList() // right parens should be less or equal to left
    left == n && right == n -> listOf(parenStr)
    else ->
      (generateParenthesis(n, left + 1, right, "$parenStr(") +
        generateParenthesis(n, left, right + 1, "$parenStr)"))
  }
