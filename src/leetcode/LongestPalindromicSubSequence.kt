/* gakshintala created on 9/23/19 */
package leetcode

fun longestPalindromeSubseq(s: String): Int {
    val table = Array(s.length) { IntArray(s.length) }
    for (i in table.indices) {
        table[i][i] = 1
    }
    for (gap in 1 until s.length) {
        for ((i, j) in (gap until s.length).withIndex()) {
            table[i][j] = if (s[i] == s[j]) {
                table[i + 1][j - 1] + 2
            } else {
                maxOf(table[i + 1][j], table[i][j - 1])
            }
        }
    }
    return table[0][s.lastIndex]
}

fun main() {
    print(longestPalindromeSubseq(readLine()!!))
}