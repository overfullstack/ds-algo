package educative.dp

/* 26 Jul 2025 21:19 */

fun numOfDecodings(decodeStr: String): Int {
  val dp = IntArray(decodeStr.length + 1) { 0 }
  dp[0] = 1
  // ! Notice `return 0`, if the first character is '0', we can't decode the string
  dp[1] = if (decodeStr[0] != '0') 1 else return 0

  // * Building the string, one index at a time
  for (endIndex in 2..(decodeStr.lastIndex + 1)) {
    if (decodeStr[endIndex - 1] != '0') {
      dp[endIndex] += dp[endIndex - 1]
    }
    if (
      decodeStr[endIndex - 2] == '1' ||
        (decodeStr[endIndex - 2] == '2' && decodeStr[endIndex - 1] <= '6')
    ) {
      dp[endIndex] += dp[endIndex - 2]
    }
  }
  return dp[decodeStr.lastIndex + 1]
}
