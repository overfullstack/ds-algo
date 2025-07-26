package educative.dp

import arrow.core.raise.result

/* 26 Jul 2025 11:41 */

fun longestPalindromicSubstring(str: String): String {
  val dp = Array(str.length) { BooleanArray(str.length) }
  for (i in 0..str.lastIndex) {
    dp[i][i] = true
  }

  lateinit var result: Pair<Int, Int>
  for (i in 0 until str.lastIndex) {
    dp[i][i + 1] = (str[i] == str[i + 1])
    if (dp[i][i + 1]) {
      result = i to i + 1
    }
  }

  for (subStrLen in 2..str.lastIndex) {
    var i = 0
    for (j in subStrLen..str.lastIndex) {
      dp[i][j] = dp[i + 1][j - 1] && (str[i] == str[j])
      if (dp[i][j]) {
        result = i to j
      }
      i++
    }
  }
  return str.substring(result.first..result.second)
}
