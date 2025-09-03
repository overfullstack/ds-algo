/* gakshintala created on 9/9/19 */
package ds.segment

import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

class SegmentTreeSum(val nums: IntArray) {
  // 2 * 2^ceil(log2(arr.size)) - 1
  private var segmentTree = IntArray(2 * 2.0.pow(ceil(log2(nums.size.toDouble()))).toInt() - 1)

  init {
    construct(0, 0, nums.lastIndex)
  }

  private fun construct(segmentTreeIndex: Int, startIndex: Int, endIndex: Int): Int =
    when (startIndex) {
      endIndex -> nums[startIndex]
      else -> {
        val mid = (startIndex + endIndex) / 2
        val leftSegmentSum = construct(2 * segmentTreeIndex + 1, startIndex, mid)
        val rightSegmentSum = construct(2 * segmentTreeIndex + 2, mid + 1, endIndex)
        leftSegmentSum + rightSegmentSum
      }
    }.also { segmentTree[segmentTreeIndex] = it }

  fun querySum(startIndex: Int, endIndex: Int): Int {
    return getSumForRange(0, nums.lastIndex, startIndex, endIndex, 0)
  }

  private fun getSumForRange(
    segmentStart: Int,
    segmentEnd: Int,
    queryStartIndex: Int,
    queryEndIndex: Int,
    segmentTreeIndex: Int,
  ): Int {
    // * Query can be broken down to multiple Segments. A Segment is a piece of the query range
    if (queryStartIndex <= segmentStart && queryEndIndex >= segmentEnd) {
      return segmentTree[segmentTreeIndex]
    }
    if (queryStartIndex > segmentEnd || queryEndIndex < segmentStart) {
      return 0
    }
    // * Break the segment and locate segment pieces that make-up the query range.
    val mid = (segmentStart + segmentEnd) / 2
    val leftSegmentSum =
      getSumForRange(segmentStart, mid, queryStartIndex, queryEndIndex, 2 * segmentTreeIndex + 1)
    val rightSegmentSum =
      getSumForRange(mid + 1, segmentEnd, queryStartIndex, queryEndIndex, 2 * segmentTreeIndex + 2)
    return leftSegmentSum + rightSegmentSum
  }

  fun update(index: Int, value: Int) {
    val diff = value - nums[index]
    nums[index] = value
    updateIndex(index, diff, 0, nums.lastIndex, 0)
  }

  private fun updateIndex(
    index: Int,
    diff: Int,
    segmentStart: Int,
    segmentEnd: Int,
    segmentTreeIndex: Int,
  ) {
    if (index !in segmentStart..segmentEnd) {
      return
    }
    // * Update the diff in all segments that contain the index.
    segmentTree[segmentTreeIndex] += diff
    if (segmentStart != segmentEnd) {
      val mid = (segmentStart + segmentEnd) / 2
      updateIndex(index, diff, segmentStart, mid, 2 * segmentTreeIndex + 1)
      updateIndex(index, diff, mid + 1, segmentEnd, 2 * segmentTreeIndex + 2)
    }
  }
}

fun main() {
  val arr = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val segmentTree = SegmentTreeSum(arr)
  queries(segmentTree)
  updates(segmentTree)
  queries(segmentTree)
}

private fun updates(segmentTreeSum: SegmentTreeSum) {
  val noOfUpdates = readln().trim().toInt()
  val updates: ArrayList<Pair<Int, Int>> = arrayListOf()
  repeat(noOfUpdates) {
    val (index, value) = readln().split(",").map { it.trim().toInt() }
    updates.add(Pair(index, value))
  }
  updates.forEach { segmentTreeSum.update(it.first, it.second) }
}

private fun queries(segmentTreeSum: SegmentTreeSum) {
  val noOfQueries = readln().trim().toInt()
  val queries: ArrayList<Pair<Int, Int>> = arrayListOf()
  repeat(noOfQueries) {
    val (startIndex, endIndex) = readln().split(",").map { it.trim().toInt() }
    queries.add(Pair(startIndex, endIndex))
  }
  queries.forEach { println(segmentTreeSum.querySum(it.first, it.second)) }
}
