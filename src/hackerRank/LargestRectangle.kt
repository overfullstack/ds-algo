/* gakshintala created on 8/23/19 */
package hackerRank

import java.util.*

// Complete the largestRectangle function below.
fun largestRectangle(h: Array<Int>): Long {
    val indexStk = ArrayDeque<Int>()
    var maxArea = Long.MIN_VALUE
    var i = 0
    while (i < h.size) {
        if (indexStk.isEmpty() || h[i] >= h[indexStk.peek()]) {
            indexStk.push(i)
            i++
        } else {
            val top = indexStk.pop()
            val areaWithTop = h[top] * if (indexStk.isEmpty()) i else i - indexStk.peek() - 1
            maxArea = maxOf(maxArea, areaWithTop.toLong())
        }
    }
    while (!indexStk.isEmpty()) {
        val top = indexStk.pop()
        val areaWithTop = h[top] * if (indexStk.isEmpty()) i else i - indexStk.peek() - 1
        maxArea = maxOf(maxArea, areaWithTop.toLong())
    }
    return maxArea
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val h = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = largestRectangle(h)

    println(result)
}