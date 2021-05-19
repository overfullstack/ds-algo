package leetcode.graph

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * Top-left to Bottom-right. 0-Empty 1-Blocked
 */
fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
    when {
        grid.isEmpty() -> return 0
        grid[0][0] == 1 || grid[grid.lastIndex][grid[0].lastIndex] == 1 -> return -1
        grid[0][0] == 0 && grid.lastIndex == 0 && grid[0].lastIndex == 0 -> return 1
    }

    var steps = 0
    val target = grid.lastIndex to grid[0].lastIndex
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(0 to 0)
    while (queue.isNotEmpty()) {
        steps++
        val size = queue.size
        repeat(size) { // This is to increment steps only for each batch/level.
            val gridPoint = queue.removeFirst()
            directions.asSequence()
                .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
                .filter { it.isValid(grid) }
                .forEach {
                    if (it == target) {
                        return steps + 1 // +1 is problem specific
                    }
                    grid[it.first][it.second] = 1 // marking visited
                    queue.add(it.first to it.second)
                }
        }
    }
    return -1
}

private val directions =
    listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, 1 to -1, -1 to 1, -1 to -1, 1 to 1)

private fun Pair<Int, Int>.isValid(grid: Array<IntArray>) =
    first >= 0 && first <= grid.lastIndex &&
        second >= 0 && second <= grid[0].lastIndex &&
        grid[first][second] == 0
