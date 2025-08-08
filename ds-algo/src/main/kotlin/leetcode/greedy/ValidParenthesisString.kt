package leetcode.greedy

/* 08 Aug 2025 13:12 */

fun checkValidString(s: String): Boolean {
  var maxOpen = 0
  var minOpen = 0
  for (ch in s) {
    when (ch) {
      '(' -> {
        maxOpen++
        minOpen++
      }
      ')' -> {
        maxOpen--
        minOpen--
      }
      '*' -> {
        maxOpen++
        minOpen--
      }
    }
    when {
      maxOpen < 0 -> return false // ! Even with all '*' we couldn't match all closed ')'
      // ! As the above condition passes, convert a few '*' to open '(' to match closed ')'
      minOpen < 0 -> minOpen = 0
    }
  }
  return minOpen == 0
}
