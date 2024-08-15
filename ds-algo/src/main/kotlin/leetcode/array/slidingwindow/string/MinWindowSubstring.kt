package leetcode.array.slidingwindow.string

/** [minimum-window-substring](https://leetcode.com/problems/minimum-window-substring/) */
fun minWindowLen(str: String, pattern: String): String {
  val patterFreqMap = pattern.groupingBy { it }.eachCount().toMutableMap()
  var start = 0
  var minWindowStart = 0
  var minWindowLen = Int.MAX_VALUE
  var matchInWindowCount = 0
  for ((i, char) in str.withIndex()) {
    patterFreqMap.computeIfPresent(char) { _, freq ->
      if (freq > 0) matchInWindowCount++
      freq.dec()
    }
    while (matchInWindowCount == pattern.length) {
      val curWindowLen = i - start + 1
      if (curWindowLen < minWindowLen) {
        minWindowStart = start
        minWindowLen = curWindowLen
      }
      patterFreqMap.computeIfPresent(str[start]) { _, freq ->
        if (freq >= 0) matchInWindowCount--
        freq.inc()
      }
      start++
    }
  }
  return if (minWindowLen == Int.MAX_VALUE) ""
  else str.substring(minWindowStart..(minWindowStart + minWindowLen))
}
