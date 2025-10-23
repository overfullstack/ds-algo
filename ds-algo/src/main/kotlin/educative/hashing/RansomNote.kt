package educative.hashing

/* 29 Jul 2025 18:26 */

/** [383. Ransom Note](https://leetcode.com/problems/ransom-note/) */
fun canConstruct(ransomNote: String, magazine: String): Boolean {
  val magazineFreq = magazine.groupingBy { it }.eachCount().toMutableMap()
  for (ch in ransomNote) {
    val currentCount = magazineFreq[ch] ?: 0
    if (currentCount == 0) {
      return false
    }
    magazineFreq[ch] = currentCount - 1
  }
  return true
}
