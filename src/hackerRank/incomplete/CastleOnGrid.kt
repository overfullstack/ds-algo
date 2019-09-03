/* gakshintala created on 9/1/19 */
package hackerRank.incomplete

import java.util.*

fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
    val grid2d = grid.map { it.toCharArray() }.toTypedArray()
    val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
    val origin = Pair(Pair(startX, startY), 0)
    val goal = Pair(goalX, goalY)
    queue.push(origin)
    while (!queue.isEmpty()) {
        val curPos = queue.pop()
        val coOrdinates = curPos.first
        if (coOrdinates == goal) {
            return curPos.second
        }
        queue.addAll(findNextUp(coOrdinates, grid2d).map { Pair(it, curPos.second + 1) })
        queue.addAll(findNextDown(coOrdinates, grid2d).map { Pair(it, curPos.second + 1) })
        queue.addAll(findNexLeft(coOrdinates, grid2d).map { Pair(it, curPos.second + 1) })
        queue.addAll(findNextRight(coOrdinates, grid2d).map { Pair(it, curPos.second + 1) })
    }
    return 0
}

fun findNextUp(
    coOrdinates: Pair<Int, Int>,
    grid2d: Array<CharArray>
): MutableList<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    var row = coOrdinates.first - 1
    while (row >= 0 && grid2d[row][coOrdinates.second] != 'X') {
        pairs.add(Pair(row, coOrdinates.second))
        row--
    }
    return pairs
}

fun findNextDown(
    coOrdinates: Pair<Int, Int>,
    grid2d: Array<CharArray>
): MutableList<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    var row = coOrdinates.first + 1
    while (row < grid2d.size
        && grid2d[row][coOrdinates.second] != 'X'
    ) {
        pairs.add(Pair(row, coOrdinates.second))
        row++
    }
    return pairs
}

fun findNexLeft(
    coOrdinates: Pair<Int, Int>,
    grid2d: Array<CharArray>
): MutableList<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    var col = coOrdinates.second - 1
    while (col >= 0
        && grid2d[coOrdinates.first][col] != 'X'
    ) {
        pairs.add(Pair(coOrdinates.first, col))
        col--
    }
    return pairs
}

fun findNextRight(
    coOrdinates: Pair<Int, Int>,
    grid2d: Array<CharArray>
): MutableList<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    var col = coOrdinates.second + 1
    while (col < grid2d[0].size
        && grid2d[coOrdinates.first][col] != 'X'
    ) {
        pairs.add(Pair(coOrdinates.first, col))
        col++
    }
    return pairs
}

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val grid = Array(n) { "" }
    for (i in 0 until n) {
        val gridItem = scan.nextLine()
        grid[i] = gridItem
    }

    val startXStartY = scan.nextLine().split(" ")

    val startX = startXStartY[0].trim().toInt()

    val startY = startXStartY[1].trim().toInt()

    val goalX = startXStartY[2].trim().toInt()

    val goalY = startXStartY[3].trim().toInt()

    val result = minimumMoves(grid, startX, startY, goalX, goalY)

    println(result)
}