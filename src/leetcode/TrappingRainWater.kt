/* gakshintala created on 8/28/19 */
package leetcode

fun main() {
    val arr = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    println(trap(arr))
}

fun trap(heights: IntArray): Int {
    var leftMax = Int.MIN_VALUE
    var rightMax = Int.MIN_VALUE
    var result = 0
    var left = 0
    var right = heights.lastIndex
    while (left < right) {
        if (heights[left] < heights[right]) {
            if (heights[left] > leftMax) {
                leftMax = heights[left]
            } else {
                result += leftMax - heights[left]
            }
            left++
        } else {
            if (heights[right] > rightMax) {
                rightMax = heights[right]
            } else {
                result += rightMax - heights[right]
            }
            right--
        }
    }
    return result
}