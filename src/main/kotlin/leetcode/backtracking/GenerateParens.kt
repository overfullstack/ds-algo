/* gakshintala created on 1/23/20 */
package leetcode.backtracking

fun generateParenthesis(
    n: Int,
    leftRemaining: Int = n,
    rightRemaining: Int = n,
    parentStr: String = ""
): List<String> =
    when {
        leftRemaining < 0 || rightRemaining < leftRemaining -> emptyList()
        leftRemaining == 0 && rightRemaining == 0 -> listOf(parentStr)
        else -> (
            generateParenthesis(n, leftRemaining - 1, rightRemaining, parentStr + "(") +
                generateParenthesis(n, leftRemaining, rightRemaining - 1, parentStr + ")")
            )
    }
