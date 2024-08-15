/* gakshintala created on 8/28/19 */
package leetcode.array

/** https://leetcode.com/problems/trapping-rain-water/ */
fun trap(heights: IntArray): Int {
  var leftLocalPeak = Int.MIN_VALUE
  var rightLocalPeak = Int.MIN_VALUE
  var result = 0
  var left = 0
  var right = heights.lastIndex
  while (left < right) {
    // * When right height is greater and current is backedup by a max, as local sub-container is
    // formed,
    // * which extends till next leftLocalPeak. Vice-versa on the right side as well
    if (heights[left] < heights[right]) {
      if (heights[left] > leftLocalPeak) {
        leftLocalPeak = heights[left]
      } else {
        result += leftLocalPeak - heights[left]
      }
      left++
    } else {
      if (heights[right] > rightLocalPeak) {
        rightLocalPeak = heights[right]
      } else {
        result += rightLocalPeak - heights[right]
      }
      right--
    }
  }
  return result
}

fun main() {
  val arr = readln().split(",").map { it.toInt() }.toIntArray()
  println(trap(arr))
}
