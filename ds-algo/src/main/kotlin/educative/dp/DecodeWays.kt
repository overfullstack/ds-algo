package educative.dp

/* 26 Jul 2025 21:19 */

/** [91. Decode Ways](https://leetcode.com/problems/decode-ways/) */
fun numOfDecodingsTopDown(
  decodeStr: String,
  idx: Int = 0,
  memo: IntArray = IntArray(decodeStr.length) { -1 },
): Int =
  when {
    // ! These recursion end after crossing lastIdx, counting a valid 1 way to decode entire string
    idx == decodeStr.length -> 1
    // ! We can't decode string after '0', it has to be clubbed with previous digit
    decodeStr[idx] == '0' -> 0
    memo[idx] != -1 -> memo[idx]
    else -> {
      // ! No `1 + fn()` as we are calculating ways to decode the entire string, not digit-by-digit
      val waysAfterOneDigit = numOfDecodingsTopDown(decodeStr, idx + 1, memo)
      val waysAfterTwoDigits =
        if (idx + 1 <= decodeStr.lastIndex && decodeStr.substring(idx..idx + 1).toInt() in 10..26) {
          numOfDecodingsTopDown(decodeStr, idx + 2, memo)
        } else 0
      (waysAfterOneDigit + waysAfterTwoDigits).also { memo[idx] = it }
    }
  }

fun numOfDecodingsBottomsUp(decodeStr: String): Int {
  val dp = IntArray(decodeStr.length + 1)
  dp[0] = 1
  // ! Notice `return 0`, if the first character is '0',
  // we can't decode it, so cannot decode the string
  dp[1] = if (decodeStr[0] != '0') 1 else return 0

  // * Building the string, one index at a time
  // * `dp[i + 1]` holds the number of ways to decode the substring `(0..i)`
  for (endIndex in 1..decodeStr.lastIndex) { // ! O(N) single pass
    if (decodeStr[endIndex] != '0') {
      dp[endIndex + 1] = dp[endIndex]
    }
    val lastTwoDigits = decodeStr.substring(endIndex - 1..endIndex)
    if (lastTwoDigits.toInt() in 10..26) {
      dp[endIndex + 1] += dp[endIndex - 1]
    }
  }
  return dp.last()
}

fun main() {
  println(numOfDecodingsBottomsUp("226")) // Should output 3
  println(numOfDecodingsBottomsUp("12")) // Should output 2
  println(numOfDecodingsBottomsUp("06")) // Should output 0

  println(numOfDecodingsTopDown("226")) // Should output 3
  println(numOfDecodingsTopDown("12")) // Should output 2
  println(numOfDecodingsTopDown("06")) // Should output 0
}
