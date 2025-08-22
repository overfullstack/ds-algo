package leetcode.dp

/* 22 Aug 2025 15:30 */

fun numDistinct(s: String, t: String): Int {
  val dp = Array(s.length + 1) { IntArray(t.length + 1) }
  for (row in dp.indices) {
    dp[row][0] = 1
  }
  for (i in 1..dp.lastIndex) {
    for (j in 1..dp.first().lastIndex) {
      // ! Without this character + With this character
      dp[i][j] = dp[i - 1][j] + if (s[i - 1] == t[j - 1]) dp[i - 1][j - 1] else 0
    }
  }
  return dp.last().last()
}
