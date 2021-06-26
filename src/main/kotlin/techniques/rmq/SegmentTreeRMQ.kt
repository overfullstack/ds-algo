/* gakshintala created on 9/9/19 */
package techniques.rmq

import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

class SegmentTreeRMQ(var arr: IntArray) {
  private var segmentTree: IntArray =
    IntArray(2 * 2.0.pow(ceil(log2(arr.size.toDouble()))).toInt() - 1) { Int.MAX_VALUE }

  fun process() {
    construct(0, 0, arr.lastIndex)
  }

  private fun construct(segmentTreeIndex: Int, startIndex: Int, endIndex: Int): Int {
    return if (startIndex == endIndex) {
      arr[startIndex]
    } else {
      val mid = (startIndex + endIndex) / 2
      minOf(
        construct(2 * segmentTreeIndex + 1, startIndex, mid),
        construct(2 * segmentTreeIndex + 2, mid + 1, endIndex)
      )
    }
  }

  fun querySum(startIndex: Int, endIndex: Int): Int {
    return getMinForRange(0, arr.size - 1, startIndex, endIndex, 0)
  }

  private fun getMinForRange(
    segmentStart: Int,
    segmentEnd: Int,
    queryStartIndex: Int,
    queryEndIndex: Int,
    segmentTreeIndex: Int
  ): Int {
    if (queryStartIndex <= segmentStart && queryEndIndex >= segmentEnd) {
      return segmentTree[segmentTreeIndex]
    }
    if (queryStartIndex > segmentEnd || queryEndIndex < segmentStart) {
      return Int.MAX_VALUE
    }
    val mid = (segmentStart + segmentEnd) / 2
    return minOf(
      getMinForRange(
        segmentStart,
        mid,
        queryStartIndex,
        queryEndIndex,
        2 * segmentTreeIndex + 1
      ),
      getMinForRange(
        mid + 1,
        segmentEnd,
        queryStartIndex,
        queryEndIndex,
        2 * segmentTreeIndex + 2
      )
    )
  }

  fun update(index: Int, value: Int) {
    arr[index] = value
    updateIndex(index, 0, arr.lastIndex, 0)
  }

  private fun updateIndex(index: Int, segmentStart: Int, segmentEnd: Int, segmentTreeIndex: Int) {
    if (index < segmentStart || index > segmentEnd) {
      return
    }
    segmentTree[segmentTreeIndex] =
      if (segmentStart != segmentEnd) {
        val mid = (segmentStart + segmentEnd) / 2
        val leftSegmentTreeIndex = 2 * segmentTreeIndex + 1
        val rightSegmentTreeIndex = 2 * segmentTreeIndex + 2
        updateIndex(index, segmentStart, mid, leftSegmentTreeIndex)
        updateIndex(index, mid + 1, segmentEnd, rightSegmentTreeIndex)
        minOf(segmentTree[leftSegmentTreeIndex], segmentTree[rightSegmentTreeIndex])
      } else {
        arr[segmentStart]
      }
  }
}

fun main() {
  val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
  val segmentTree = SegmentTreeRMQ(arr)
  segmentTree.process()

  queries(segmentTree)

  updates(segmentTree)

  queries(segmentTree)
}

private fun updates(segmentTreeRMQ: SegmentTreeRMQ) {
  val noOfUpdates = readLine()!!.trim().toInt()
  val updates = mutableListOf<Pair<Int, Int>>()
  repeat(noOfUpdates) {
    val (index, value) = readLine()!!.split(",").map { it.trim().toInt() }
    updates += index to value
  }
  updates.forEach { segmentTreeRMQ.update(it.first, it.second) }
}

private fun queries(
  segmentTreeRMQ: SegmentTreeRMQ
) {
  val noOfQueries = readLine()!!.trim().toInt()
  val queries = mutableListOf<Pair<Int, Int>>()
  repeat(noOfQueries) {
    val (startIndex, endIndex) = readLine()!!.split(",").map { it.trim().toInt() }
    queries += startIndex to endIndex
  }
  queries.forEach { println(segmentTreeRMQ.querySum(it.first, it.second)) }
}
