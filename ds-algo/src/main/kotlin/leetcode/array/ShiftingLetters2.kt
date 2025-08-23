package leetcode.array

/* 08 Aug 2025 07:52 */

/** [2381. Shifting Letters II](https://leetcode.com/problems/shifting-letters-ii/) ⏱️ TLE */
fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
  var result = s
  for ((start, end, direction) in shifts) {
    result =
      result.replaceRange(
        start..end,
        result
          .substring(start..end)
          .map {
            val shift = if (direction == 1) 1 else -1
            val newCharCode = (it - 'a' + shift + 26) % 26
            ('a' + newCharCode)
          }
          .joinToString(""),
      )
  }
  return result
}

fun main() {
  // s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
  println(
    shiftingLetters("abc", arrayOf(intArrayOf(0, 1, 0), intArrayOf(1, 2, 1), intArrayOf(0, 2, 1)))
  )
  // "dztz", shifts = [[0,0,0],[1,1,1]]
  println(shiftingLetters("dztz", arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 1))))
}
