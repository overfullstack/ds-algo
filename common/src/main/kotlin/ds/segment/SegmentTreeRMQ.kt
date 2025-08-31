/* gakshintala created on 9/9/19 */
package ds.segment

import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

/** Range Minimum Query (RMQ) */
class SegmentTreeRMQ(val nums: IntArray) {
  // 2 * 2^ceil(log2(arr.size)) - 1
  private var segmentTree: IntArray =
    IntArray(2 * 2.0.pow(ceil(log2(nums.size.toDouble()))).toInt() - 1) { Int.MAX_VALUE }

  init {
    construct(0, 0, nums.lastIndex)
  }

  private fun construct(segmentTreeIndex: Int, startIndex: Int, endIndex: Int): Int =
    when (startIndex) {
      endIndex -> nums[startIndex]
      else -> {
        val mid = (startIndex + endIndex) / 2
        minOf(
          construct(2 * segmentTreeIndex + 1, startIndex, mid),
          construct(2 * segmentTreeIndex + 2, mid + 1, endIndex),
        )
      }
    }.also { segmentTree[segmentTreeIndex] = it }

  fun queryMin(startIndex: Int, endIndex: Int): Int {
    return getMinForRange(0, nums.size - 1, startIndex, endIndex, 0)
  }

  private fun getMinForRange(
    segmentStart: Int,
    segmentEnd: Int,
    queryStartIndex: Int,
    queryEndIndex: Int,
    segmentTreeIndex: Int,
  ): Int {
    if (queryStartIndex <= segmentStart && queryEndIndex >= segmentEnd) {
      return segmentTree[segmentTreeIndex]
    }
    if (queryStartIndex > segmentEnd || queryEndIndex < segmentStart) {
      return Int.MAX_VALUE
    }
    val mid = (segmentStart + segmentEnd) / 2
    return minOf(
      getMinForRange(segmentStart, mid, queryStartIndex, queryEndIndex, 2 * segmentTreeIndex + 1),
      getMinForRange(mid + 1, segmentEnd, queryStartIndex, queryEndIndex, 2 * segmentTreeIndex + 2),
    )
  }

  fun update(index: Int, value: Int) {
    nums[index] = value
    updateIndex(index, 0, nums.lastIndex, 0)
  }

  private fun updateIndex(index: Int, segmentStart: Int, segmentEnd: Int, segmentTreeIndex: Int) {
    if (index !in segmentStart..segmentEnd) {
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
        nums[segmentStart]
      }
  }
}

fun main() {
  val arr = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val segmentTree = SegmentTreeRMQ(arr)
  queries(segmentTree)
  updates(segmentTree)
  queries(segmentTree)
}

private fun updates(segmentTreeRMQ: SegmentTreeRMQ) {
  val noOfUpdates = readln().trim().toInt()
  val updates = mutableListOf<Pair<Int, Int>>()
  repeat(noOfUpdates) {
    val (index, value) = readln().split(",").map { it.trim().toInt() }
    updates += index to value
  }
  updates.forEach { segmentTreeRMQ.update(it.first, it.second) }
}

private fun queries(segmentTreeRMQ: SegmentTreeRMQ) {
  val noOfQueries = readln().trim().toInt()
  val queries = mutableListOf<Pair<Int, Int>>()
  repeat(noOfQueries) {
    val (startIndex, endIndex) = readln().split(",").map { it.trim().toInt() }
    queries += startIndex to endIndex
  }
  queries.forEach { println(segmentTreeRMQ.queryMin(it.first, it.second)) }
}
