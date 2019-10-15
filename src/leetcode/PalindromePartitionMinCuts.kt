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
            cutsTable[i][j] = if (isPalTable[i][j]) 0 else
                (i until j).fold(Int.MAX_VALUE) { min, partition ->
                    minOf(min, cutsTable[i][partition] + 1 + cutsTable[partition + 1][j])
                }
        }
    }
    return cutsTable[0][s.lastIndex]
}

fun main() {
    print(minCutsForPalindromePartition(readLine()!!))
}