package cci.recursion

/**
 * This is same as UniquePaths DP problem.
 */
fun findRoboPath(
    grid: Array<BooleanArray>,
    row: Int = 0,
    col: Int = 0,
    failedPoints: MutableSet<Pair<Int, Int>> = mutableSetOf(),
    path: List<Pair<Int, Int>> = emptyList()
): List<Pair<Int, Int>> {
    if (row > grid.lastIndex || col > grid.lastIndex || !grid[row][col] || failedPoints.contains(Pair(row, col))) {
        return emptyList()
    }
    if (row == grid.lastIndex && col == grid[0].lastIndex) {
        return path + Pair(row, col)
    }
    var curPath = findRoboPath(grid, row, col + 1, failedPoints, path + Pair(row, col))
    if (curPath.isNotEmpty()) {
        return curPath
    }
    curPath = findRoboPath(grid, row + 1, col, failedPoints, path + Pair(row, col))
    if (curPath.isNotEmpty()) {
        return curPath
    }

    failedPoints.add(Pair(row, col))
    return emptyList()
}
