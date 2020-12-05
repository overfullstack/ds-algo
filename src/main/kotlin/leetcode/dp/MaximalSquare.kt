package leetcode.dp

/**
 * https://leetcode.com/problems/maximal-square/
 */
fun maximalSquare(matrix: Array<CharArray>): Int {
    if (matrix.isEmpty()) {
        return 0
    }
    val table = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }
    var maxSize = Int.MIN_VALUE

    for (row in 1..matrix.size) {
        for (col in 1..matrix[0].size) {
            if (matrix[row - 1][col - 1] == '1') {
                table[row][col] = minOf(table[row - 1][col], table[row][col - 1], table[row - 1][col - 1]) + 1
                maxSize = maxOf(maxSize, table[row][col])
            }
        }
    }
    return maxSize * maxSize // Asked for area
}
