package leetcode.slidingwindow

/* 26 Sep 2025 12:13 */

/**
 * [28. Find the Index of the First Occurrence in a
 * String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)
 */
fun strStr(haystack: String, needle: String): Int {
  if (haystack.length < needle.length) {
    return -1
  }
  for (idx in 0..haystack.lastIndex - needle.lastIndex) {
    var needleIdx = 0
    while (needleIdx <= needle.lastIndex && haystack[idx + needleIdx] == needle[needleIdx]) {
      needleIdx++
    }
    if (needleIdx == needle.lastIndex + 1) {
      return idx
    }
  }
  return -1
}

fun main() {
  println(strStr("mississippi", "issip")) // 4
  println(strStr("mississippi", "issipi")) // -1
  println(strStr("sadbutsad", "sad")) // 0
  println(strStr("leetcode", "leeto")) // -1
}
