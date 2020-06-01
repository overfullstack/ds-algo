/* gakshintala created on 8/23/19 */
package leetcode.stack

import java.util.*

fun largestRectangle(histogram: Array<Int>): Long {
    val indexStk = Stack<Int>()
    var histIndex = 0
    var maxArea = 0
    while (histIndex <= histogram.lastIndex) {
        while (indexStk.isNotEmpty() && histogram[histIndex] < histogram[indexStk.peek()]) {
            val top = indexStk.pop() // `pop()` exposes the windowStart on the stack
            val windowStart = if (indexStk.isNotEmpty()) indexStk.peek() else -1
            val windowEnd = histIndex - 1
            maxArea = maxOf(maxArea, histogram[top] * (windowEnd - windowStart))
        }
        indexStk.push(histIndex)
        histIndex++
    }
    
    while (indexStk.isNotEmpty()) {
        val top = indexStk.pop()
        val windowStart = if (indexStk.isNotEmpty()) indexStk.peek() else -1
        // `windowEnd` for the left over is `lastIndex`.
        maxArea = maxOf(maxArea, histogram[top] * (histogram.lastIndex - windowStart))
    }
    return maxArea.toLong()
}

fun largestRectangle_g4g(histogram: Array<Int>): Long {
    val indexStk = Stack<Int>()
    var maxArea = Long.MIN_VALUE
    var histToInsertIndex = 0
    while (histToInsertIndex <= histogram.lastIndex) {
        if (indexStk.isEmpty() || histogram[histToInsertIndex] >= histogram[indexStk.peek()]) { // accumulate as bigger hists are encountered.
            indexStk.push(histToInsertIndex)
            histToInsertIndex++
        } else {
            val top = indexStk.pop()
            // This is equivalent to getting area with every hist or all hists higher than this.
            // Observe we are operating on indexes, so even the previously popped hists come into count.
            val areaWithTop =
                histogram[top] * if (indexStk.isEmpty()) histToInsertIndex else histToInsertIndex - indexStk.peek() - 1
            maxArea = maxOf(maxArea, areaWithTop.toLong())
        }
    }
    while (indexStk.isNotEmpty()) { // This area calculation is in reverse order.
        val top = indexStk.pop()
        val areaWithTop = histogram[top] * if (indexStk.isEmpty()) histogram.size else histogram.size - indexStk.peek() - 1
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
