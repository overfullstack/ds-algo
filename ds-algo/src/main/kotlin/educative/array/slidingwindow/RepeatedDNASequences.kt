package educative.array.slidingwindow

import kotlin.math.pow

/* 01 Sep 2024 09:24 */

fun repeatedDNASequences(str: String, k: Int): Set<String> {
  val charArr = arrayOf('A', 'C', 'G', 'T')
  val numMap = charArr.mapIndexed { index, char -> char to index + 1 }.toMap()
  val numStr = str.map { numMap[it]!! }
  val a: Double = charArr.size.toDouble()
  var hashForSubStr =
    numStr.take(k).foldIndexed(0) { index, hash, charNum ->
      hash + charNum * a.pow(k - 1 - index).toInt()
    }
  val hashSet = mutableSetOf<Int>(hashForSubStr)
  val result = mutableSetOf<String>()
  for (i in (1..str.lastIndex - k + 1)) {
    val prevHashForSubStr = hashForSubStr
    hashForSubStr =
      (prevHashForSubStr - numStr[i - 1] * a.pow(k - 1).toInt()) * a.toInt() + numStr[i + k - 1]
    val isAbsent = hashSet.add(hashForSubStr)
    if (!isAbsent) {
      result.add(str.substring(i..i + k - 1))
    }
  }
  return result
}
