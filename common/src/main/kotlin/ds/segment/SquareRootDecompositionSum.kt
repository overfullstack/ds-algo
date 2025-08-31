/* gakshintala created on 9/6/19 */
package ds.segment

import kotlin.math.sqrt

class SquareRootDecompositionSum(val arr: IntArray) {
  private var sumArr = IntArray(arr.size) { 0 }
  private var chunkSize: Int = 0

  init {
    chunkSize = sqrt(arr.size.toDouble()).toInt()
    sumArr = arr.asSequence().chunked(chunkSize).map { it.sum() }.toList().toIntArray()
  }

  fun querySum(startIndex: Int, endIndex: Int): Int {
    if (startIndex !in 0..endIndex || endIndex > arr.lastIndex) {
      return -1
    }
    if (startIndex == endIndex) {
      return 0
    }

    val startBlock = startIndex / chunkSize
    val endBlock = endIndex / chunkSize

    val startBlockStartIndex = startBlock * chunkSize
    val endBlockEndIndex = (endBlock * chunkSize) + chunkSize

    return (sumArr.slice(startBlock..endBlock).sum() -
      arr.slice(startBlockStartIndex until startIndex).sum() - // Excluding StartIndex.
      arr.slice(endIndex + 1 until endBlockEndIndex).sum()) // Excluding EndIndex.
  }

  fun update(index: Int, value: Int) {
    val windowIndex = index / chunkSize
    sumArr[windowIndex] = sumArr[windowIndex] - arr[index] + value
    arr[index] = value
  }
}

fun main() {
  val arr = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val squareRootDecomposition = SquareRootDecompositionSum(arr)
  val noOfQueries = readln().trim().toInt()
  repeat(noOfQueries) {
    val (startIndex, endIndex) = readln().split(",").map { it.trim().toInt() }
    println(squareRootDecomposition.querySum(startIndex, endIndex))
  }
}
