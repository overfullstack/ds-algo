package educative.dp

/* 26 Jul 2025 11:41 */

fun longestPalindromicSubstring(str: String): String {
  val dp = Array(str.length) { BooleanArray(str.length) }
  for (i in 0..str.lastIndex) {
    dp[i][i] = true
  }

  lateinit var result: Pair<Int, Int>
  // ! For windowLen 2
  for (i in 0 until str.lastIndex) {
    dp[i][i + 1] = (str[i] == str[i + 1])
    if (dp[i][i + 1]) {
      result = i to i + 1
    }
  }

  // * Build substrings from smaller windows to larger windows
  // ! Note we are building the window, not the String
  for (windowLen in 2..str.lastIndex) {
    for ((wStart, wEnd) in (windowLen..str.lastIndex).withIndex()) {
      dp[wStart][wEnd] = dp[wStart + 1][wEnd - 1] && (str[wStart] == str[wEnd])
      if (dp[wStart][wEnd]) {
        result = wStart to wEnd // ! Increasing window, so always the max
      }
    }
  }
  return str.substring(result.first..result.second)
}
