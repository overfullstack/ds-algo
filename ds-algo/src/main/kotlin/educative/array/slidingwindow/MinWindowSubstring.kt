package educative.array.slidingwindow

/* 16 Sep 2024 18:05 */
fun minWindowSubstring(s: String, t: String): String {
  val tFreq = t.groupingBy { it }.eachCount().toMutableMap()
  var start = 0
  var end = 0
  var minWindowSize = Int.MAX_VALUE
  var minWinStart = 0
  var found = 0
  for((i, char) in s.withIndex()) {
    tFreq.computeIfPresent(char) { _, freq -> 
      if (freq > 0) found++
      freq.dec()
    }
    while (found == t.length) {
      val curWindowSize = end - start + 1
      if (curWindowSize < minWindowSize) {
        if (curWindowSize == t.length) {
          return s.substring(start..i)
        }
        minWindowSize = curWindowSize
        minWinStart = start
      }
      tFreq.computeIfPresent(s[start]) { _, freq -> 
        if (freq >= 0) found--
        freq.inc()
      }
      start++
    }
  }
  return if (minWindowSize == Int.MAX_VALUE) "" else s.substring(minWinStart..minWinStart + minWindowSize - 1)
}
