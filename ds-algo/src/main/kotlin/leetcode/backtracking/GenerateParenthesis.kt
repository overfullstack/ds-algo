/* gakshintala created on 1/23/20 */
package leetcode.backtracking

fun generateParenthesis(
  n: Int,
  left: Int = 0,
  right: Int = 0,
  parenStr: String = "",
): List<String> =
  when (left) {
    // ! left count should be greater than right. right is capped by left and left is capped at n
    !in right..n -> emptyList()
    n if right == n -> listOf(parenStr)
    else ->
      (generateParenthesis(n, left + 1, right, "$parenStr(") +
        generateParenthesis(n, left, right + 1, "$parenStr)"))
  }

fun main() {
  println(generateParenthesis(3))
}
