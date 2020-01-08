/* gakshintala created on 1/2/20 */
package leetcode.arrays

fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    var windowStart = 0
    var maxWindow = 0
    val freqInWindow = mutableMapOf<Char, Int>()
    for ((index, char) in s.withIndex()) {
        freqInWindow.merge(char, 1) { curFreq, _ -> curFreq.inc() }
        while (freqInWindow.size > k) {
            freqInWindow.computeIfPresent(s[windowStart]) { _, freq ->
                windowStart++ // Move start towards right
                if (freq == 1) {
                    null // remove entry, to decrease map size
                } else {
                    freq.dec()
                }
            }
        }
        maxWindow = maxOf(maxWindow, index - windowStart + 1)
    }
    return maxWindow
}