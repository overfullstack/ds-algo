/* gakshintala created on 1/2/20 */
package leetcode.slidingwindow.string

/**
 * [3. Longest Substring Without Repeating
 * Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
 */
fun lengthOfLongestSubstringWithoutCharRepeat(s: String): Int {
  val map = hashMapOf<Char, Int>()
  var maxWindow = 0
  var windowStart = 0
  for ((index, char) in s.withIndex()) {
    map.merge(char, index) { lastOccurrence, curOccurrence ->
      // ! The char `lastOccurrence` may be before `windowStart` (harmless)
      // ! as we retain the char entry in the hashMap even after `windowStart` surpassed it.
      // ! Or it can be after or on `windowStart`.
      // ! +1 excluding last occurrence.
      windowStart = maxOf(windowStart, lastOccurrence + 1)
      curOccurrence // Always update `curOccurrence`
    }
    // ! This is outside of merge coz, if all chars are unique, merge might never be called.
    maxWindow = maxOf(maxWindow, index - windowStart + 1)
  }
  return maxWindow
}
