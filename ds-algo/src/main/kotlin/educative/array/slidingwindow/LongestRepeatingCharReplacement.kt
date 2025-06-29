package educative.array.slidingwindow

/* 17 Sep 2024 12:14 */
fun longestRepeatingCharReplacement(s: String, k: Int): Int {
  var maxWindow = Int.MIN_VALUE
  var start = 0
  var maxFreq = Int.MIN_VALUE
  var freqMap = mutableMapOf<Char, Int>()
  for ((i, char) in s.withIndex()) {
    freqMap.compute(char) { _, value ->
      val newValue = value?.inc() ?: 1
      maxFreq = maxOf(maxFreq, newValue)
      newValue
    }
    if (i - start + 1 - maxFreq > k) {
      freqMap.computeIfPresent(s[start]) { _, value -> value.dec() }
      start++
    }
    maxWindow = maxOf(maxWindow, i - start + 1)
  }
  return maxWindow
}

fun main() {
  println(longestRepeatingCharReplacement("bbbbbbbbb", 2))
}
