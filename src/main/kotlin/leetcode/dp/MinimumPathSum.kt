/* gakshintala created on 12/29/19 */
package leetcode.dp

fun minPathSumRecursive(grid: Array<IntArray>, row: Int, col: Int): Int {
    return when {
        row < 0 || col < 0 -> return Int.MAX_VALUE
        row == 0 && col == 0 -> return grid[0][0]
        else -> grid[row][col] + minOf(minPathSumRecursive(grid, row + 1, col), minPathSumRecursive(grid, row, col + 1))
    }

}