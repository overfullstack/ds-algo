/* gakshintala created on 1/2/20 */
package leetcode.arrays.slidingwindow

fun lengthOfLongestSubstringWithoutCharRepeat(s: String): Int {
    val map = hashMapOf<Char, Int>()
    var maxWindow = 0
    var windowStart = 0
    for ((index, char) in s.withIndex()) {
        map.merge(char, index) { lastOccurrence, curOccurrence ->
            // The char `lastOccurance` may be before `windowStart` (harmless),
            // as we retain the char entry in the hashMap even after `windowStart` surpassed it.
            // Or it can be after or on `windowStart`
            windowStart = maxOf(windowStart, lastOccurrence + 1) // ! +1 to start from after the last occurance.
            curOccurrence // Always update `curOccurance`
        }
        // This is outside of merge coz, if all chars are unique, merge might never be called.
        maxWindow = maxOf(maxWindow,index - windowStart + 1)
    }
    return maxWindow
}
