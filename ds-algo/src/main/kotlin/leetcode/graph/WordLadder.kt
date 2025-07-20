package leetcode.graph

fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
  val wordSet = wordList.toMutableSet()
  if (endWord !in wordSet) return 0

  var start = ArrayDeque<String>().apply { add(beginWord) }
  var end = ArrayDeque<String>().apply { add(endWord) }
  wordSet.removeAll(listOf(beginWord, endWord))

  var ladderLength = 0
  while (start.isNotEmpty()) {
    ladderLength++
    repeat(start.size) {
      val word = start.removeFirst()
      for (i in word.indices) {
        for (ch in 'a'..'z') {
          val transform = word.replaceRange(i, i + 1, ch.toString())
          when (transform) {
            in end -> return ladderLength + 1
            in wordSet -> {
              start.add(transform)
              wordSet.remove(transform)
            }
          }
        }
      }
    }
    if (start.size > end.size) { // Bidirectional BFS, swapping start with the end.
      start = end.also { end = start }
    }
  }
  return 0
}
