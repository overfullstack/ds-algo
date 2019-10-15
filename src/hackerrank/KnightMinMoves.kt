/* gakshintala created on 9/2/19 */
package hackerrank

import java.util.*

fun knightlOnAChessboard(n: Int): Array<Array<Int>> {
    val pairOffSet = (1 until n).map { x -> (1 until n).map { y -> Pair(x, y) } }
    return pairOffSet.map { pairs -> pairs.map { findMinStepsToReachEnd(it, n) }.toTypedArray() }.toTypedArray()
}

fun findMinStepsToReachEnd(pairOffset: Pair<Int, Int>, n: Int): Int {
    val offSetCombinations = offSetCombinations(pairOffset)
    val visited = Array(n) { _ -> BooleanArray(n) }
    val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
    queue.push(Pair(Pair(0, 0), 0))
    visited[0][0] = true
    while (queue.isNotEmpty()) {
        val (xy, steps) = queue.pop()
        if (xy == Pair(n - 1, n - 1)) {
            return steps
        }
        val (x, y) = xy
        queue.addAll(
            offSetCombinations
                .map { Pair(it.first + x, it.second + y) }
                .filter { pairInBounds(it, n) }
                .filterNot { visited[it.first][it.second] }
                .map {
                    visited[it.first][it.second] = true
                    Pair(it, steps + 1)
                }
        )
    }
    return -1
}

fun pairInBounds(pair: Pair<Int, Int>, n: Int) = pair.first in 0 until n && pair.second in 0 until n

private fun offSetCombinations(pairOffset: Pair<Int, Int>): List<Pair<Int, Int>> {
    val swapPairOffSet = Pair(pairOffset.second, pairOffset.first)
    return listOf(pairOffset, swapPairOffSet)
        .flatMap {
            listOf(
                Pair(it.first, it.second),
                Pair(it.first, -it.second),
                Pair(-it.first, it.second),
                Pair(-it.first, -it.second)
            )
        }
}

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val result = knightlOnAChessboard(n)

    println(result.joinToString("\n") { it.joinToString(" ") })
}