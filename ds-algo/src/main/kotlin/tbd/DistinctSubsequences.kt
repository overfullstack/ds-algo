fun numDistinct(s: String, t: String): Int {
  val m = s.length
  val n = t.length

  // Edge case: if target is longer than source, no subsequences possible
  if (n > m) return 0

  // Edge case: if target is empty, there's exactly one way (empty subsequence)
  if (n == 0) return 1

  // Edge case: if source is empty but target is not, no subsequences possible
  if (m == 0) return 0

  // DP table: dp[i][j] = number of ways to form t[0..j-1] from s[0..i-1]
  val dp = Array(m + 1) { IntArray(n + 1) { 0 } }

  // Base case: empty string can be formed from any string in exactly one way
  // dp[i][0] = 1 for all i (empty subsequence)
  for (i in 0..m) {
    dp[i][0] = 1
  }

  // Fill the DP table
  for (i in 1..m) {
    for (j in 1..n) {
      if (s[i - 1] == t[j - 1]) {
        // If characters match, we have two options:
        // 1. Use this character: dp[i-1][j-1] (match current character)
        // 2. Skip this character: dp[i-1][j] (ignore current character)
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
      } else {
        // If characters don't match, we can only skip current character
        // dp[i][j] = dp[i-1][j] (ignore current character)
        dp[i][j] = dp[i - 1][j]
      }
    }
  }

  return dp[m][n]
}
