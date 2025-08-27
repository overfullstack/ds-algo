package educative.slidingwindow

import kotlin.math.pow

/* 01 Sep 2024 09:24 */

fun repeatedDNASequences(str: String, k: Int): Set<String> {
  val dnaSequenceComposition = arrayOf('A', 'C', 'G', 'T')
  val numMap = dnaSequenceComposition.mapIndexed { index, char -> char to index + 1 }.toMap()
  val numStr = str.map { numMap[it]!! }
  val dnaCompositionSize: Double = dnaSequenceComposition.size.toDouble()
  var hashForSubStr =
    numStr.take(k).foldIndexed(0) { index, hash, charNum ->
      hash + charNum * dnaCompositionSize.pow(k - 1 - index).toInt()
    }
  val hashSet = mutableSetOf(hashForSubStr)
  val result = mutableSetOf<String>()
  for (i in (1..str.lastIndex - k + 1)) {
    val prevHashForSubStr = hashForSubStr
    hashForSubStr =
      (prevHashForSubStr - numStr[i - 1] * dnaCompositionSize.pow(k - 1).toInt()) *
        dnaCompositionSize.toInt() + numStr[i + k - 1]
    val isAbsent = hashSet.add(hashForSubStr)
    if (!isAbsent) {
      result.add(str.substring(i..i + k - 1))
    }
  }
  return result
}
