/* gakshintala created on 8/23/19 */
package leetcode.stack

fun largestRectangle(histogram: Array<Int>): Long {
  val indexStk = ArrayDeque<Int>()
  var maxArea = 0
  for (histIndex in histogram.indices) {
    // * A bar can make rectangle till it's next lowest on the right.
    // * When a lower bar than `last()` is encountered, `pop()` bars from right to left,
    // * calculating area made by EACH bar left till righ most.
    while (indexStk.isNotEmpty() && histogram[histIndex] < histogram[indexStk.last()]) {
      val top = indexStk.removeLast()
      // `leftStart` can be immediate previous or few higher rectangles away which were popped while
      // inserting this.
      // So using this `leftStart` takes all those higher rectangles into account
      val leftStart = if (indexStk.isNotEmpty()) indexStk.last() else -1
      // `histIndex - 1` represents rightEnd
      // * The window expands with `leftStart` going left, right shall be fixed always
      // Area is calculated excluding `leftStart`, as it is smaller than `top`
      val areaWithCurrentTop = histogram[top] * (histIndex - 1 - leftStart)
      maxArea = maxOf(maxArea, areaWithCurrentTop)
    }
    indexStk.add(histIndex)
  }

  while (indexStk.isNotEmpty()) {
    val top = indexStk.removeFirst()
    val leftStart = if (indexStk.isNotEmpty()) indexStk.first() else -1
    // rightEnd for all the left overs is `lastIndex`.
    maxArea = maxOf(maxArea, histogram[top] * (histogram.lastIndex - leftStart))
  }
  return maxArea.toLong()
}

fun largestRectangle_g4g(histogram: Array<Int>): Long {
  val indexStk = ArrayDeque<Int>()
  var maxArea = Long.MIN_VALUE
  var histToInsertIndex = 0
  while (histToInsertIndex <= histogram.lastIndex) {
    if (
      indexStk.isEmpty() || histogram[histToInsertIndex] >= histogram[indexStk.last()]
    ) { // accumulate as bigger hists are encountered.
      indexStk.add(histToInsertIndex)
      histToInsertIndex++
    } else {
      val top = indexStk.removeLast()
      // This is equivalent to getting area with every hist or all hists higher than this.
      // Observe we are operating on indexes, so even the previously popped hists come into count.
      val areaWithTop =
        histogram[top] *
          if (indexStk.isEmpty()) histToInsertIndex else histToInsertIndex - indexStk.last() - 1
      maxArea = maxOf(maxArea, areaWithTop.toLong())
    }
  }
  while (indexStk.isNotEmpty()) { // This area calculation is in reverse order.
    val top = indexStk.removeLast()
    val areaWithTop =
      histogram[top] *
        if (indexStk.isEmpty()) histogram.size else histogram.size - indexStk.removeLast() - 1
    maxArea = maxOf(maxArea, areaWithTop.toLong())
  }
  return maxArea
}
