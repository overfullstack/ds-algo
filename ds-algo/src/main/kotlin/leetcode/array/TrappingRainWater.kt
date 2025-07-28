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
    // * When the right height is greater and current is backed up by a max, as local sub-container
    // is formed, which extends till the next `leftLocalPeak`. Vice versa on the right side as well
    when {
      heights[left] < heights[right] -> {
        when {
          heights[left] > leftLocalPeak -> leftLocalPeak = heights[left]
          // This is possible because, both leftLocalPeak and curLeft
          // are backed-up by the rightLocalPeak
          else -> result += leftLocalPeak - heights[left]
        }
        left++
      }

      else -> {
        when {
          heights[right] > rightLocalPeak -> rightLocalPeak = heights[right]
          else -> result += rightLocalPeak - heights[right]
        }
        right--
      }
    }
  }
  return result
}

fun main() {
  val arr = readln().split(",").map { it.toInt() }.toIntArray()
  println(trap(arr))
}
