package leetcode.array


/* 31 Jul 2025 17:20 */

fun shortestWay(source: String, target: String): Int {
  var ways = 0
  var t = 0
  while (t <= target.lastIndex) {
    var s = 0
    var foundAtleastOneMatch = false
    while (s <= source.lastIndex && t <= target.lastIndex) {
      if (source[s] == target[t]) {
        foundAtleastOneMatch = true
        t++
      }
      s++
    }
    if (!foundAtleastOneMatch) return -1
    ways++
  }
  return ways
}

fun main() {
  println(shortestWay("abc", "abcbc")) // 2
  println(shortestWay("abc", "acdbc")) // -1
  println(shortestWay("abc", "abc")) // 1
  println(shortestWay("xyz", "xzyxz")) // 3
}
