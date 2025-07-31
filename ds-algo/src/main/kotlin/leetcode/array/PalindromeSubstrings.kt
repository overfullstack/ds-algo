package leetcode.array

fun countSubstrings(s: String): Int =
  s.indices.sumOf { stretchWindow(s, it, it) + stretchWindow(s, it, it + 1) }

private fun stretchWindow(s: String, start: Int, end: Int): Int {
  var count = 0
  var startIndex = start
  var endIndex = end
  while (startIndex >= 0 && endIndex <= s.lastIndex && s[startIndex] == s[endIndex]) {
    count++
    startIndex--
    endIndex++
  }
  return count
}
