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
    // * A bar can make rectangle till it's next lowest on the right.
    // * When a lower bar than `peek()` is encountered, `pop()` bars from right to left,
    // * calculating area made by each bar left till right most.
    while (indexStk.isNotEmpty() && histogram[i] < histogram[indexStk.peek()]) {
      val top = indexStk.pop()
      // `leftIndex` can be immediate previous or few higher rectangles away
      // which were popped while inserting this.
      // So using this `leftIndex` takes all those higher rectangles into account
      // `-1` is used for the case when `indexStk` is empty, to get width of entire histogram array
      val leftIndex = if (indexStk.isNotEmpty()) indexStk.peek() else -1
      // * The window expands with `leftIndex` going left, right shall be fixed always
      // * Area is calculated between two smaller bars
      // `leftIndex` and `rightIndex (i)`, excluding both
      // * `(i - leftIndex - 1)` as both `leftIndex` and `rightIndex` are excluded.
      // `leftIndex` gives the range of all the larger bars than `top` to the left excluding itself,
      // as it should be smaller than `top`
      // `rightIndex (i)` is anyway excluded as it is smaller than top
      val areaWithCurrentTop = histogram[top] * (i - leftIndex - 1)
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
