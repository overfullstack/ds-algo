package educative.twopointers

/* 28 Jul 2025 08:26 */

fun containerWithMostWater(heights: IntArray): Int {
  var maxArea = 0
  var left = 0
  var right = heights.lastIndex

  while (right > left) {
    val width = right - left
    maxArea = maxOf(maxArea, minOf(heights[left], heights[right]) * width)
    when {
      // * As we squeeze width, we gain vertical area only moving from shorter to longer lines
      heights[left] < heights[right] -> left++
      else -> right--
    }
  }
  return maxArea
}
