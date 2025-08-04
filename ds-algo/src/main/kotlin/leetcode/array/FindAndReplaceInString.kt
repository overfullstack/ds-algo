package leetcode.array

/* 04 Aug 2025 18:49 */

/** [833. Find And Replace in String](https://leetcode.com/problems/find-and-replace-in-string) */
fun findReplaceString(
  s: String,
  indices: IntArray,
  sources: Array<String>,
  targets: Array<String>,
): String =
  indices
    .zip(sources)
    .zip(targets)
    .map { Triple(it.first.first, it.first.second, it.second) }
    .sortedByDescending { it.first }
    .fold(s) { result, (i, source, target) ->
      val range = i until i + source.length
      when {
        range.last > result.lastIndex -> result
        s.substring(range) == source -> result.replaceRange(range, target)
        else -> result
      }
    }

fun main() {
  println(
    findReplaceString("abcdef", intArrayOf(2, 2), arrayOf("cdef", "feg"), arrayOf("feg", "abc"))
  )
  println(
    findReplaceString(
      "abcde",
      intArrayOf(2, 2, 3),
      arrayOf("cde", "cdef", "dk"),
      arrayOf("fe", "f", "xyz"),
    )
  )
  println(findReplaceString("abcd", intArrayOf(0, 2), arrayOf("a", "cd"), arrayOf("eee", "ffff")))
  println(findReplaceString("abcd", intArrayOf(0, 2), arrayOf("ab", "ec"), arrayOf("eee", "ffff")))
}
