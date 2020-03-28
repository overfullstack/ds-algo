/* gakshintala created on 1/23/20 */
package leetcode.backtracking

fun generateParenthesis(n: Int): List<String> = if (n == 0) emptyList() else generateParenthesisUtil(n)

fun generateParenthesisUtil(
    n: Int,
    leftRemaining: Int = n,
    rightRemaining: Int = n,
    parentStr: String = ""
): List<String> {
    if (leftRemaining < 0 || rightRemaining < leftRemaining) { // `rightRemaining < leftRemaining` which means we have exhausted all rightRemaining
        return emptyList()
    }
    if (leftRemaining == 0 && rightRemaining == 0) {
        return listOf(parentStr)
    }
    return (generateParenthesisUtil(n, leftRemaining - 1, rightRemaining, "$parentStr(")
            + generateParenthesisUtil(n, leftRemaining, rightRemaining - 1, "$parentStr)"))
}
