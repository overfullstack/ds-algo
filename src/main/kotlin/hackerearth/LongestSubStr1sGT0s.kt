/* gakshintala created on 5/24/20 */
package hackerearth

fun longestSubStr(nums: IntArray): Int {
    var maxWindow = 0
    val map = mutableMapOf(0 to -1)
    var count = 0
    for ((i, num) in nums.withIndex()) {
        count += (if (num == 1) 1 else -1)
        map.putIfAbsent(count, i)
        if (map.containsKey(count - 1)) { // This can be extended for -k in place of -1, 0 for same 1s = 0s
            maxWindow = maxOf(maxWindow, i - map[count - 1]!!)
        }
    }
    return maxWindow
}

fun main() {
    readLine()
    val nums = readLine()!!.split("").map { it.toInt() }.toIntArray()
    println(longestSubStr(nums))
}
