/* gakshintala created on 9/9/19 */
package techniques.sumforrange

import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

class SegmentTreeSum(var arr: IntArray) {
    private var segmentTree = IntArray(2 * 2.0.pow(ceil(log2(arr.size.toDouble()))).toInt() - 1)
    fun process() {
        construct(0, 0, arr.lastIndex)
    }

    private fun construct(segmentTreeIndex: Int, startIndex: Int, endIndex: Int): Int {
        segmentTree[segmentTreeIndex] =
            if (startIndex == endIndex) {
                arr[startIndex]
            } else { // Going into mids of mids and ending up at individual arr elements. 
                val mid = (startIndex + endIndex) / 2
                construct(2 * segmentTreeIndex + 1, startIndex, mid) +
                    construct(2 * segmentTreeIndex + 2, mid + 1, endIndex)
            }
        return segmentTree[segmentTreeIndex]
    }

    fun querySum(startIndex: Int, endIndex: Int): Int {
        return getSumForRange(0, arr.lastIndex, startIndex, endIndex, 0)
    }

    private fun getSumForRange(
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
            return 0
        }
        val mid = (segmentStart + segmentEnd) / 2
        return getSumForRange(
            segmentStart, mid,
            queryStartIndex, queryEndIndex,
            2 * segmentTreeIndex + 1
        ) + getSumForRange(
            mid + 1, segmentEnd,
            queryStartIndex, queryEndIndex,
            2 * segmentTreeIndex + 2
        )
    }

    fun update(index: Int, value: Int) {
        val diff = value - arr[index]
        arr[index] = value
        updateIndex(index, diff, 0, arr.lastIndex, 0)
    }

    private fun updateIndex(
        index: Int,
        diff: Int,
        segmentStart: Int,
        segmentEnd: Int,
        segmentTreeIndex: Int
    ) {
        if (index < segmentStart || index > segmentEnd) {
            return
        }
        segmentTree[segmentTreeIndex] += diff
        if (segmentStart != segmentEnd) {
            val mid = (segmentStart + segmentEnd) / 2
            updateIndex(index, diff, segmentStart, mid, 2 * segmentTreeIndex + 1)
            updateIndex(index, diff, mid + 1, segmentEnd, 2 * segmentTreeIndex + 2)
        }
    }
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    val segmentTree = SegmentTreeSum(arr)
    segmentTree.process()

    queries(segmentTree)

    updates(segmentTree)

    queries(segmentTree)
}

private fun updates(segmentTreeSum: SegmentTreeSum) {
    val noOfUpdates = readLine()!!.trim().toInt()
    val updates: ArrayList<Pair<Int, Int>> = arrayListOf()
    repeat(noOfUpdates) {
        val (index, value) = readLine()!!.split(",").map { it.trim().toInt() }
        updates.add(Pair(index, value))
    }
    updates.forEach { segmentTreeSum.update(it.first, it.second) }
}

private fun queries(segmentTreeSum: SegmentTreeSum) {
    val noOfQueries = readLine()!!.trim().toInt()
    val queries: ArrayList<Pair<Int, Int>> = arrayListOf()
    repeat(noOfQueries) {
        val (startIndex, endIndex) = readLine()!!.split(",").map { it.trim().toInt() }
        queries.add(Pair(startIndex, endIndex))
    }
    queries.forEach { println(segmentTreeSum.querySum(it.first, it.second)) }
}
