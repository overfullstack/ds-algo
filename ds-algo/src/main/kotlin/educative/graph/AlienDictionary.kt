package educative.graph

import ds.graph.DiGraph

/* 30 Aug 2024 10:46 */

fun orderAlienDictionary(words: List<String>): String {
  val diGraph = DiGraph<Char>()
  val charToInConnectionCount = words.flatMap { it.toList() }.associateWith { 0 }.toMutableMap()
  for (i in (0 until words.lastIndex)) {
    val unmatchedResult = firstUnmatchedLetterPair(words[i], words[i + 1])
    unmatchedResult?.fold(
      { (fromChar, toChar) ->
        if (diGraph[fromChar]?.contains(toChar) != true) {
          diGraph.addEdge(fromChar, toChar)
          charToInConnectionCount.computeIfPresent(toChar) { _, value -> value.inc() }
        }
      },
      {
        return "" // ! If there is an exception
      },
    )
  }
  val result = mutableListOf<Char>()
  // Adding leaf nodes to queue
  val queue = ArrayDeque(charToInConnectionCount.filterValues { it == 0 }.map { it.key })
  while (queue.isNotEmpty()) {
    val nextChar = queue.removeFirst()
    result += nextChar
    diGraph[nextChar]?.forEach {
      charToInConnectionCount.computeIfPresent(it) { _, inConnectionCount ->
        when (inConnectionCount) {
          1 -> {
            queue.add(it)
            null
          }
          else -> inConnectionCount.dec()
        }
      }
    }
  }

  return when {
    result.size < charToInConnectionCount.size -> ""
    else -> result.joinToString("")
  }
}

fun firstUnmatchedLetterPair(word1: String, word2: String): Result<Pair<Char, Char>>? =
  runCatching {
    var i = 0
    while (i <= word1.lastIndex) {
      require(i <= word2.lastIndex)
      if (word1[i] != word2[i]) {
        return Result.success(word1[i] to word2[i])
      }
      i++
    }
    return null
  }
