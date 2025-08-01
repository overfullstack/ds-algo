package leetcode.array


/* 01 Aug 2025 14:35 */

class RLEIterator(val encoding: IntArray) {
  var i = 0
  
  fun next(n: Int): Int {
    var n1 = n
    while(i < encoding.lastIndex && n1 > encoding[i]) {
      n1 -= encoding[i]
      i += 2
    }
    return when {
        i > encoding.lastIndex -> -1
        else -> {
            encoding[i] -= n1
            encoding[i + 1]
        }
    }
  }
}
