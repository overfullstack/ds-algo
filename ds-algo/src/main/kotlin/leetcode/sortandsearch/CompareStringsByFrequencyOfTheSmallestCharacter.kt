package leetcode.sortandsearch

/* 10 Aug 2025 09:02 */

/**
 * [1170. Compare Strings by Frequency of the Smallest
 * Character](https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character)
 */
fun numSmallerByFrequency(queries: Array<String>, words: Array<String>): IntArray {
  val queriesFreq = queries.map { smallCharFreq(it) }
  val wordsFreq = words.map { smallCharFreq(it) }.sorted()
  return queriesFreq.map { words.lastIndex - firstLargerNumIndex(it, wordsFreq) + 1 }.toIntArray()
}

fun smallCharFreq(str: String): Int =
  str.groupingBy { it }.eachCount().toSortedMap().firstEntry().value

tailrec fun firstLargerNumIndex(
  targetNum: Int,
  wordsFreq: List<Int>,
  left: Int = 0,
  right: Int = wordsFreq.lastIndex,
): Int =
  when {
    // ! This happens only when even the wordsFreq.lastIndex is <= targetNum
    left == right -> if (wordsFreq[right] <= targetNum) right + 1 else right
    else -> {
      val mid = left + (right - left) / 2
      when {
        wordsFreq[mid] <= targetNum -> firstLargerNumIndex(targetNum, wordsFreq, mid + 1, right)
        else -> firstLargerNumIndex(targetNum, wordsFreq, left, mid)
      }
    }
  }

fun main() {
  println(
    numSmallerByFrequency(
        arrayOf(
          "aabbabbb",
          "abbbabaa",
          "aabbbabaa",
          "aabba",
          "abb",
          "a",
          "ba",
          "aa",
          "ba",
          "baabbbaaaa",
          "babaa",
          "bbbbabaa",
        ),
        arrayOf(
          "b",
          "aaaba",
          "aaaabba",
          "aa",
          "aabaabab",
          "aabbaaabbb",
          "ababb",
          "bbb",
          "aabbbabb",
          "aab",
          "bbaaababba",
          "baaaaa",
        ),
      )
      .joinToString()
  )
  println(numSmallerByFrequency(arrayOf("cbd"), arrayOf("zaaaz")).joinToString())
  println(
    numSmallerByFrequency(arrayOf("bbb", "cc"), arrayOf("a", "aa", "aaa", "aaaa")).joinToString()
  )
}
