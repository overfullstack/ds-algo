package educative.hashing

/* 20 Jul 2025 08:50 */

/** [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/) */
fun isIsomorphic(s: String, t: String): Boolean {
  if (s.length != t.length) return false
  val sToT = mutableMapOf<Char, Char>()
  val tToS = mutableMapOf<Char, Char>()
  return s.zip(t).all { (sChar, tChar) ->
    when {
      sToT[sChar] != tToS[tChar] -> false
      else -> {
        sToT[sChar] = tChar
        tToS[tChar] = sChar
        true
      }
    }
  }
}
