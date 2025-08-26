package educative.dp

/* 26 Jul 2025 21:19 */

/** [91. Decode Ways](https://leetcode.com/problems/decode-ways/) */
fun numOfDecodings(decodeStr: String): Int {
  val dp = IntArray(decodeStr.length + 1)
  dp[0] = 1
  // ! Notice `return 0`, if the first character is '0',
  // we can't decode it, so cannot decode the string
  dp[1] = if (decodeStr[0] != '0') 1 else return 0

  // * Building the string, one index at a time
  // * `dp[i + 1]` holds the number of ways to decode the substring `(0..i)`
  for (endIndex in 1..decodeStr.lastIndex) {
    if (decodeStr[endIndex] != '0') {
      dp[endIndex + 1] = dp[endIndex]
    }
    val lastTwoDigits = decodeStr.substring(endIndex - 1..endIndex)
    if (lastTwoDigits.toInt() in 10..26) {
      dp[endIndex + 1] += dp[endIndex - 1]
    }
  }
  return dp[decodeStr.lastIndex + 1]
}

fun main() {
  println(numOfDecodings("226")) // Should output 3
  println(numOfDecodings("12")) // Should output 2
  println(numOfDecodings("06")) // Should output 0
}
