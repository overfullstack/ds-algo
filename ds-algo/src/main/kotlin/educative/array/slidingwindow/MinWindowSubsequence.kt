package educative.array.slidingwindow

/* 04 Sep 2024 17:51 */
fun minWindowSubsequence(str1: String, str2: String): String {
  var minWinLen = Int.MAX_VALUE
  var i1 = 0
  var minWinSubsequence = ""
  while (i1 <= str1.lastIndex) {
    var i2 = 0
    while (i1 <= str1.lastIndex && i2 <= str2.lastIndex) {
      if (str1[i1] == str2[i2]) {
        i2++
      }
      i1++
    }
    if (i2 != str2.length) {
      continue
    }
    i1--
    i2--
    var winEnd = i1
    while (i1 >= 0 && i2 >= 0) {
      if (str1[i1] == str2[i2]) {
        i2--
      }
      i1--
    }
    i1++
    val curWindowLen = winEnd - i1 + 1
    if (minWinLen > curWindowLen) {
      minWinLen = curWindowLen
      minWinSubsequence = str1.substring(i1..winEnd)
    }
    i1++
  }
  return minWinSubsequence
}
