package educative.slidingwindow

/* 04 Sep 2024 17:51 */

/** Finds the minimum window substring in str1 that contains str2 as a subsequence */
fun minWindowSubsequence(str1: String, str2: String): String =
  when {
    str2.isEmpty() -> ""
    str1.isEmpty() -> ""
    else -> findMinimumWindow(str1, str2)
  }

/** Finds the minimum window by checking all possible starting positions */
private fun findMinimumWindow(str1: String, str2: String): String =
  (0..str1.lastIndex)
    .asSequence()
    .mapNotNull { str1StartIndex ->
      // * Find the end first, go in reverse to eliminate the redundancy at the beginning
      findWindowEnd(str1, str2, str1StartIndex)?.let { windowEnd ->
        // ! Go in reverse and find the window start
        val windowStart = findWindowStart(str1, str2, windowEnd)
        windowStart to windowEnd
      }
    }
    .minByOrNull { it.second - it.first }
    ?.let { str1.substring(it.first..it.second) } ?: ""

private tailrec fun findWindowEnd(
  str1: String,
  str2: String,
  str1StartIndex: Int,
  str2StartIndex: Int = 0,
): Int? =
  when {
    str1StartIndex > str1.lastIndex -> null
    str2StartIndex > str2.lastIndex -> str1StartIndex - 1
    str1[str1StartIndex] == str2[str2StartIndex] ->
      findWindowEnd(str1, str2, str1StartIndex + 1, str2StartIndex + 1)
    else -> findWindowEnd(str1, str2, str1StartIndex + 1, str2StartIndex)
  }

private tailrec fun findWindowStart(
  str1: String,
  str2: String,
  str1EndIndex: Int,
  str2EndIndex: Int = str2.lastIndex,
): Int =
  when {
    // `str1EndIndex < 0 -> 0` Not Checking as we already found a valid window in str1
    str2EndIndex < 0 -> str1EndIndex + 1
    str1[str1EndIndex] == str2[str2EndIndex] ->
      findWindowStart(str1, str2, str1EndIndex - 1, str2EndIndex - 1)
    else -> findWindowStart(str1, str2, str1EndIndex - 1, str2EndIndex)
  }
