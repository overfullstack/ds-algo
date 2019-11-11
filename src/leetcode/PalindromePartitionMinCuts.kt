/* gakshintala created on 9/24/19 */
package leetcode

fun minCutsForPalindromePartition(s: String): Int {
    val cutsTable = Array(s.length) { IntArray(s.length) }
    val isPalTable = Array(s.length) { BooleanArray(s.length) }

    for (index in cutsTable.indices) {
        cutsTable[index][index] = 0
        isPalTable[index][index] = true
    }

    for (gap in 1..s.lastIndex) {
        for ((i, j) in (gap..s.lastIndex).withIndex()) {
            isPalTable[i][j] = if (gap == 1) s[i] == s[j] else s[i] == s[j] && isPalTable[i + 1][j - 1]
            cutsTable[i][j] =
                if (isPalTable[i][j]) 0
                else (i until j).map { cutsTable[i][it] + 1 + cutsTable[it + 1][j] }.min() ?: 0
        }
    }
    return cutsTable[0][s.lastIndex]
}

fun main() {
    print(minCutsForPalindromePartition(readLine()!!))
}