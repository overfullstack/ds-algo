package educative.dp

/* 26 Jul 2025 18:05 */

/** [Word Break](https://leetcode.com/problems/word-break/) */
fun wordBreakDP(s: String, wordDict: List<String>): Boolean {
  val dp = BooleanArray(s.length + 1)
  val dict = wordDict.toSet()
  dp[0] = true
  // * Building the word, one index at a time, expanding `endIndex` and checking if it can be broken
  for (endIndex in 1..(s.lastIndex + 1)) {
    for (startIndex in 0..endIndex) {
      if (dp[startIndex] && dict.contains(s.substring(startIndex until endIndex))) {
        dp[endIndex] = true
        break
      }
    }
  }
  return dp[s.lastIndex + 1]
}
