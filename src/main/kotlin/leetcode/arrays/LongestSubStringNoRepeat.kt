/* gakshintala created on 1/2/20 */
package leetcode.arrays

fun lengthOfLongestSubstringWithoutCharRepeat(s: String): Int {
    val map = hashMapOf<Char, Int>()
    var maxWindow = 0
    var windowStart = 0
    for ((index, char) in s.withIndex()) {
        map.merge(char, index) { lastOccurrence, curOccurrence ->
            // maxOf is required, as we need the later most of previous `windowStart` and `lastOccurrence`
            // We can't blindly take the lastOccurrence, as prev windowStart may be later than that, which indicates a repeating number pointing at prev windowStart. 
            windowStart = maxOf(windowStart, lastOccurrence + 1) 
            curOccurrence
        }
        maxWindow = maxOf(maxWindow, index - windowStart + 1)
    }
    return maxWindow
}
