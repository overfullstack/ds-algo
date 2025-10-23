package educative.stack

/* 27 Aug 2024 09:33 */

/**
 * [1047. Remove All Adjacent Duplicates In
 * String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
 */
fun removeAdjacentDuplicates(str: String): String {
  val stk = ArrayDeque<Char>()
  for (ch in str) {
    if (stk.isNotEmpty() && stk.last() == ch) {
      stk.removeLast()
    } else {
      stk.add(ch)
    }
  }
  return stk.joinToString("")
}
