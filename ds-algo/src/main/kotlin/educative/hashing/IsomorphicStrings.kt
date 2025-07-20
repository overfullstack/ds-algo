package educative.hashing

/* 20 Jul 2025 08:50 */

fun isIsomorphic(s: String, t: String): Boolean {
  if (s.length != t.length) return false

  val sToT = mutableMapOf<Char, Char>()
  val tToS = mutableMapOf<Char, Char>()

  return s.zip(t).all { (sChar, tChar) ->
    when {
      sToT[sChar] != null && sToT[sChar] != tChar -> false
      tToS[tChar] != null && tToS[tChar] != sChar -> false
      else -> {
        sToT[sChar] = tChar
        tToS[tChar] = sChar
        true
      }
    }
  }
}
