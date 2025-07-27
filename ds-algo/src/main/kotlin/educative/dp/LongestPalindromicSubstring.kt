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

  // * Build substrings from smaller windows to larger windows
  for (windowLen in 2..str.lastIndex) {
    for ((wStart, wEnd) in (windowLen..str.lastIndex).withIndex()) {
      dp[wStart][wEnd] = dp[wStart + 1][wEnd - 1] && (str[wStart] == str[wEnd])
      if (dp[wStart][wEnd]) {
        result = wStart to wEnd
      }
    }
  }
  return str.substring(result.first..result.second)
}
