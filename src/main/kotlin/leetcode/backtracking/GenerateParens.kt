/* gakshintala created on 1/23/20 */
package leetcode.backtracking

fun generateParenthesis(
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
    return (generateParenthesis(n, leftRemaining - 1, rightRemaining, "$parentStr(")
            + generateParenthesis(n, leftRemaining, rightRemaining - 1, "$parentStr)"))
}
