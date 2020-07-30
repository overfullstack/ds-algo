package leetcode.graph

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

@ExperimentalStdlibApi
fun orangesRotting(grid: Array<IntArray>): Int {
    if (grid.isEmpty()) {
        return 0
    }
    val queue = ArrayDeque<Pair<Int, Int>>()
    var freshCount = 0

    for (row in grid.indices) {
        for (col in grid[0].indices) {
            when (grid[row][col]) {
                2 -> queue.add(row to col)
                1 -> freshCount++
            }
        }
    }

    when {
        freshCount == 0 -> return 0
        queue.isEmpty() -> return -1
    }

    var count = 0
    while (queue.isNotEmpty()) {
        count++
        val size = queue.size
        repeat(size) {
            val rottonOrangeGridPoint = queue.removeFirst()
            freshCount -= directions.asSequence()
                .map { (rottonOrangeGridPoint.first + it.first) to (rottonOrangeGridPoint.second + it.second) }
                .filter { it.isValid(grid) }
                .onEach {
                    grid[it.first][it.second] = 2
                    queue.add(it.first to it.second)
                }
                .count()
        }
    }

    return if (freshCount == 0) count - 1 else -1 // count - 1 as you will hv an extra loop after all oranges are rotton.
}

private fun Pair<Int, Int>.isValid(grid: Array<IntArray>) =
    first >= 0 && first <= grid.lastIndex &&
            second >= 0 && second <= grid[0].lastIndex &&
            grid[first][second] == 1
