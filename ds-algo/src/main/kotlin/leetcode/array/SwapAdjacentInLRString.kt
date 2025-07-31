package leetcode.array

/* 31 Jul 2025 12:55 */

fun canTransform(start: String, end: String): Boolean {
  if (start.replace("X", "") != end.replace("X", "")) {
    return false
  }

  var p1 = 0
  var p2 = 0

  while (p1 <= start.lastIndex && p2 <= end.lastIndex) {
    while (p1 <= start.lastIndex && start[p1] == 'X') {
      p1++
    }
    while (p2 <= end.lastIndex && end[p2] == 'X') {
      p2++
    }
    when {
      p1 == (start.lastIndex + 1) && p2 == (end.lastIndex + 1) -> return true
      p1 == (start.lastIndex + 1) || p2 == (end.lastIndex + 1) -> return false
      start[p1] != end[p2] -> return false
      start[p1] == 'L' && p1 < p2 -> return false
      start[p1] == 'R' && p1 > p2 -> return false
    }
    p1++
    p2++
  }
  return true
}

fun main() {
  println(canTransform("RXXLRXRXL", "XRLXXRRLX"))
}
