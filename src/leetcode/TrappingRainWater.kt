/* gakshintala created on 8/28/19 */
package leetcode

fun main() {
    val arr = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    println(trap(arr))
}

fun trap(height: IntArray): Int {
    var leftMax = Int.MIN_VALUE
    var rightMax = Int.MIN_VALUE
    var result = 0
    var low = 0
    var high = height.size - 1
    while (low < high) {
        if (height[low] < height[high]) {
            if (height[low] > leftMax) {
                leftMax = height[low]
            } else {
                result += leftMax - height[low]
            }
            low++
        } else {
            if (height[high] > rightMax) {
                rightMax = height[high]
            } else {
                result += rightMax - height[high]
            }
            high--
        }
    }
    return result
}