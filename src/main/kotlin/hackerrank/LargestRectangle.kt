/* gakshintala created on 8/23/19 */
package hackerrank

import java.util.*

fun largestRectangle(histogram: Array<Int>): Long {
    val indexStk = ArrayDeque<Int>()
    var maxArea = Long.MIN_VALUE
    var histToInsertIndex = 0
    while (histToInsertIndex < histogram.size) {
        if (indexStk.isEmpty() || histogram[histToInsertIndex] >= histogram[indexStk.peek()]) { // accumulate as bigger hists are encountered. 
            indexStk.push(histToInsertIndex)
            histToInsertIndex++
        } else {
            val top = indexStk.pop()
            // This is equivalent to getting area with every hist or all hists higher than this. 
            // Observe we are operating on indexes, so even the popped hists come into count.
            val areaWithTop = histogram[top] * if (indexStk.isEmpty()) histToInsertIndex else histToInsertIndex - indexStk.peek() - 1
            maxArea = maxOf(maxArea, areaWithTop.toLong())
        }
    }
    while (!indexStk.isEmpty()) {
        val top = indexStk.pop()
        val areaWithTop = histogram[top] * if (indexStk.isEmpty()) histToInsertIndex else histToInsertIndex - indexStk.peek() - 1
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
