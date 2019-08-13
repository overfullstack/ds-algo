/* gakshintala created on 8/9/19 */
package hackerRank

import java.util.*

fun isBalanced(s: String): String {
    if (s.isEmpty()) {
        return "YES"
    }
    val stk = Stack<Char>()
    var result = false
    for (c in s) {
        when (c) {
            '[', '{', '(' -> stk.push(c)
            ']', '}', ')' -> {
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
    return "YES"
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val t = scan.nextLine().trim().toInt()

    for (tItr in 1..t) {
        val s = scan.nextLine()

        val result = isBalanced(s)

        println(result)
    }
}
