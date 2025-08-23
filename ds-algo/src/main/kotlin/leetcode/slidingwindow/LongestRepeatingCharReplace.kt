package leetcode.slidingwindow

/** https://leetcode.com/problems/longest-repeating-character-replacement/ */
fun characterReplacement(s: String, k: Int): Int {
  var start = 0
  val freqMap = IntArray(26)
  var maxCharFreq = Int.MIN_VALUE
  for ((i, char) in s.withIndex()) {
    freqMap[char - 'A']++
    maxCharFreq = maxOf(maxCharFreq, freqMap[char - 'A'])
    // ! `+1` to include the current character
    if ((i - start + 1) > k + maxCharFreq) {
      freqMap[s[start] - 'A']--
      start++
    }
  }
  return s.lastIndex - start + 1 // This is either increasing or constant, so don't need `maxOf`
}
