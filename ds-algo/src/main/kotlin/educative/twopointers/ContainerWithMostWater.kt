package educative.twopointers

/* 28 Jul 2025 08:26 */

fun containerWithMostWater(heights: IntArray): Int {
  var maxArea = 0
  var start = 0
  var end = heights.lastIndex

  while (end > start) {
    val width = end - start
    maxArea = maxOf(maxArea, minOf(heights[start], heights[end]) * width)
    when {
      heights[start] < heights[end] -> start++
      else -> end--
    }
  }
  return maxArea
}
