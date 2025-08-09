package leetcode.array

/* 09 Aug 2025 18:23 */

fun licenseKeyFormatting(s: String, k: Int): String {
  val str = s.filter { it != '-' }
  return str
    .map { it.uppercaseChar() }
    .foldRightIndexed("") { i, ch, result ->
      when {
        result.isNotEmpty() && (str.lastIndex - i).mod(k) == 0 -> "$ch-$result"
        else -> "$ch$result"
      }
    }
}

fun main() {
  println(licenseKeyFormatting("5F3Z-2e-9-w", 4)) // 5F3Z-2E9W
  println(licenseKeyFormatting("2-5g-3-J", 2)) // 2-5G-3J
}
