package leetcode.graph

fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
  val wordSet = wordList.toMutableSet()
  if (endWord !in wordSet) {
    return 0
  }

  var start = ArrayDeque<String>()
  start.add(beginWord)
  wordSet.remove(beginWord)
  var end = ArrayDeque<String>()
  end.add(endWord)
  wordSet.remove(endWord)

  var length = 0
  while (start.isNotEmpty()) {
    length++
    val size = start.size
    repeat(size) {
      val word = start.removeFirst()
      for (i in word.indices) {
        for (c in 'a'..'z') {
          when (
            val transform =
              word.slice(0 until i) + c + word.slice(i + 1..word.lastIndex)
          ) {
            in end -> return length + 1
            in wordSet -> {
              start.add(transform)
              wordSet.remove(transform)
            }
          }
        }
      }
    }
    if (start.size > end.size) { // Bi-directional DFS, swapping start with end.
      start = end.also { end = start }
    }
  }
  return 0
}
