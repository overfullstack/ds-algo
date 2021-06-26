package leetcode.arrays.slidingwindow.string

/**
 * 🔒 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 *
 */
fun lengthOfLongestSubstringAtmostKDistinct(s: String, k: Int): String {
  val freqInWindow = mutableMapOf<Char, Int>()
  var start = 0
  var maxWindowLen = Int.MIN_VALUE
  var maxWindowStart = 0
  for ((index, char) in s.withIndex()) {
    freqInWindow.merge(char, 1) { freq, _ -> freq.inc() }
    if (freqInWindow.size > k) {
      val curWindowLen = index - start // ! No +1 as we exclude the char which increased size
      if (curWindowLen > maxWindowLen) {
        maxWindowStart = start
        maxWindowLen = curWindowLen
      }

      while (freqInWindow.size > k) {
        freqInWindow.compute(s[start]) { _, freq ->
          when (freq) {
            1 -> null
            else -> freq!!.dec()
          }
        }
        start++
      }
    }
  }

  return if (maxWindowLen == Int.MIN_VALUE) s else s.substring(
    maxWindowStart,
    maxWindowStart + maxWindowLen
  )
}

fun main() {
  println(lengthOfLongestSubstringAtmostKDistinct("aabbcc", 3))
}
