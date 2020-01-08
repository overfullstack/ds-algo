/* gakshintala created on 12/26/19 */
package leetcode.dp

fun uniquePaths(m: Int, n: Int): Int {
    val table = Array(m) { IntArray(n) }

    (0 until m).forEach { table[it][0] = 1 }
    (0 until n).forEach { table[0][it] = 1 }

    for (i in 1 until m) {
        for (j in 1 until n) {
            table[i][j] = table[i - 1][j] + table[i][j - 1]
        }
    }
    return table[m - 1][n - 1]
}

fun main() {
    print(uniquePaths(readLine()!!.toInt(), readLine()!!.toInt()))
}