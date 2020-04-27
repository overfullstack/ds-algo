/* gakshintala created on 1/2/20 */
package leetcode.arrays

fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    var windowStart = 0
    var maxWindow = 0
    val freqInWindow = mutableMapOf<Char, Int>()
    for ((index, char) in s.withIndex()) {
        freqInWindow.merge(char, 1) { curFreq, _ -> curFreq.inc() }
        // If unique chars are more than required, we trim from start until unique char count is brought back to k, so we can loop ahead
        while (freqInWindow.size > k) {  
            freqInWindow.computeIfPresent(s[windowStart]) { _, freq ->
                windowStart++ // Move start towards right, index acts as the end
                if (freq == 1) {
                    null // remove entry, to decrease map size
                } else {
                    freq.dec()
                }
            }
        }
        maxWindow = maxOf(maxWindow, index - windowStart + 1) // maxWindow is calculated in every iteration.
    }
    return maxWindow
}
