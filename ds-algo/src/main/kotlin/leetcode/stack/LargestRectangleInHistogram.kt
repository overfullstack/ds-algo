/* gakshintala created on 8/23/19 */
package leetcode.stack

import java.util.Stack

/**
 * [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
 */
fun largestRectangle(histogram: IntArray): Long {
  val indexStk = Stack<Int>()
  var maxArea = 0
  for (i in histogram.indices) {
    // * Smaller bar can make rectangle with larger bar, but larger bar can't make with smaller bar
    // * So when a smaller bar is encountered, pop all larger bars from stack calculating the area
    // they can make with other larger bars (on their right)
    while (indexStk.isNotEmpty() && histogram[i] < histogram[indexStk.peek()]) {
      val top = indexStk.pop()
      val leftIndex = if (indexStk.isNotEmpty()) indexStk.peek() else -1
      // Pop previous bars, starting with index `i - 1`
      val areaWithCurrentTop = histogram[top] * (i - 1 - leftIndex)
      maxArea = maxOf(maxArea, areaWithCurrentTop)
    }
    indexStk.add(i)
  }

  while (indexStk.isNotEmpty()) {
    val top = indexStk.pop()
    val leftIndex = if (indexStk.isNotEmpty()) indexStk.peek() else -1
    // rightEnd for all the leftovers is `lastIndex`.
    maxArea = maxOf(maxArea, histogram[top] * (histogram.lastIndex - leftIndex))
  }
  return maxArea.toLong()
}

fun main() {
  largestRectangle(intArrayOf(2, 4, 5, 6, 3))
}
