/* gakshintala created on 1/2/20 */
package leetcode.arrays.subarray

fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    var start = 0
    var maxWindow = 0
    val freqInWindow = mutableMapOf<Char, Int>()
    for ((index, char) in s.withIndex()) { // Checking having each index as end
        freqInWindow.merge(char, 1) { curFreq, _ -> curFreq.inc() }
        // If unique chars are more than required, we trim from start until unique char count is brought back to k, so we can loop ahead
        while (freqInWindow.size > k) {
            freqInWindow.computeIfPresent(s[start]) { _, freq ->
                start++ // Trim from start for every index (end)
                if (freq == 1) {
                    null // remove entry, to decrease map size
                } else {
                    freq.dec()
                }
            }
        }
        maxWindow = maxOf(maxWindow, index - start + 1) // maxWindow checked for each index
    }
    return maxWindow
}
