package educative.graph

import ds.graph.DiGraph

/* 30 Aug 2024 10:46 */

fun orderAlienDictionary(words: List<String>): String {
  val diGraph = DiGraph<Char>()
  val charToInConnectionCount = words.flatMap { it.toList() }.associateWith { 0 }.toMutableMap()
  for (i in (0 until words.lastIndex)) {
    val unmatchedResult = firstUnmatchedLetterPair(words[i], words[i + 1])
    unmatchedResult?.fold(
      { (char1, char2) ->
        if (diGraph[char1]?.contains(char2) != true) {
          diGraph.addEdge(char1, char2)
          charToInConnectionCount.computeIfPresent(char2) { _, value -> value.inc() }
        }
      },
      {
        return ""
      },
    )
  }
  val result = mutableListOf<Char>()
  val queue = ArrayDeque(charToInConnectionCount.filterValues { it == 0 }.map { it.key })
  while (queue.isNotEmpty()) {
    val nextChar = queue.removeFirst()
    result += nextChar
    diGraph[nextChar]?.forEach {
      charToInConnectionCount.computeIfPresent(it) { _, inConnectionCount ->
        when (val newValue = inConnectionCount.dec()) {
          0 -> {
            queue.add(it)
            null
          }
          else -> newValue
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
