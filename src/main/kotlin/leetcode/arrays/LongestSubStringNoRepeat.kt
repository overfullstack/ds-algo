/* gakshintala created on 1/2/20 */
package leetcode.arrays

fun lengthOfLongestSubstringWithoutCharRepeat(s: String): Int {
    val map = hashMapOf<Char, Int>()
    var maxWindow = 0
    var windowStartForChar = 0
    for ((index, char) in s.withIndex()) {
        map.merge(char, index) { lastOccurrence, curOccurrence ->
            windowStartForChar = maxOf(windowStartForChar, lastOccurrence + 1) // maxOf is required, as `lastOccurrence` can be less than `windowStart`
            curOccurrence
        }
        maxWindow = maxOf(maxWindow, index - windowStartForChar + 1)
    }
    return maxWindow
}