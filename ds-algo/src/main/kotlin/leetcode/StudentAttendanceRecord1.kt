package leetcode

/* 10 Aug 2025 11:23 */

/** [551. Student Attendance Record I]https://leetcode.com/problems/student-attendance-record-i */
fun checkRecord(s: String): Boolean {
  var aCount = 0
  var lCount = 0
  for (ch in s) {
    when (ch) {
      'L' -> lCount++
      'A' -> {
        aCount++
        lCount = 0
      }
      else -> lCount = 0
    }
    if (aCount == 2 || lCount == 3) {
      return false
    }
  }
  return true
}

fun main() {
  println(checkRecord("PPALLP"))
  println(checkRecord("PPALLL"))
  println(checkRecord("LALL"))
}
