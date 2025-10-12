package educative.elegant.slidingwindow

/* 04 Sep 2024 17:51 */

/** [857 · Minimum Window Subsequence](https://www.lintcode.com/problem/857/) */
fun minWindowSubsequence(s: String, t: String): String =
  when {
    s.isEmpty() -> ""
    t.isEmpty() -> ""
    else -> findMinimumWindow(s, t)
  }

/** Finds the minimum window by checking all possible starting positions */
private fun findMinimumWindow(s: String, t: String): String =
  s.indices
    .mapNotNull { str1StartIndex ->
      // * Find the end first, go in reverse to eliminate the redundancy at the beginning
      findWindowEnd(s, t, str1StartIndex)?.let { windowEnd ->
        // ! Go in reverse and find the window start
        val windowStart = findWindowStart(s, t, windowEnd)
        windowStart to windowEnd
      }
    }
    .minByOrNull { it.second - it.first }
    ?.let { s.substring(it.first..it.second) } ?: ""

private tailrec fun findWindowEnd(s: String, t: String, sIdx: Int, tIdx: Int = 0): Int? =
  when {
    tIdx > t.lastIndex -> sIdx - 1 // ! This has be above `sIdx > s.lastIndex`
    sIdx > s.lastIndex -> null
    s[sIdx] == t[tIdx] -> findWindowEnd(s, t, sIdx + 1, tIdx + 1)
    else -> findWindowEnd(s, t, sIdx + 1, tIdx)
  }

private tailrec fun findWindowStart(s: String, t: String, sIdx: Int, tIdx: Int = t.lastIndex): Int =
  when {
    // `sEndIndex < 0 -> 0` Not Checking as we already found a valid window in str1
    tIdx < 0 -> sIdx + 1
    s[sIdx] == t[tIdx] -> findWindowStart(s, t, sIdx - 1, tIdx - 1)
    else -> findWindowStart(s, t, sIdx - 1, tIdx)
  }
