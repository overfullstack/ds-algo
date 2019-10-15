/* gakshintala created on 8/23/19 */
package hackerrank

import java.util.*

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

fun main() {
    val scan = Scanner(System.`in`)
    
    val h = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = largestRectangle(h)

    println(result)
}