/* gakshintala created on 9/6/19 */
package techniques.sumforrange

import kotlin.math.sqrt

class SquareRootDecompositionSum(var arr: MutableList<Int>) {
    private var sumArr = mutableListOf<Int>()
    private var chunkSize: Int = 0

    fun preProcess() {
        chunkSize = sqrt(arr.size.toDouble()).toInt()
        sumArr = arr.asSequence()
            .chunked(chunkSize)
            .map { it.sum() }
            .toMutableList()
    }

    fun query(startIndex: Int, endIndex: Int): Int {
        if (startIndex > endIndex || startIndex < 0 || endIndex > arr.lastIndex) {
            return -1
        }
        if (startIndex == endIndex) {
            return 0
        }

        val startBlock = startIndex / chunkSize
        val endBlock = endIndex / chunkSize

        val startBlockStartIndex = startBlock * chunkSize
        val endBlockEndIndex = (endBlock * chunkSize) + chunkSize

        return (
            sumArr.slice(startBlock..endBlock).sum() -
                arr.slice(startBlockStartIndex until startIndex).sum() - // Excluding StartIndex.
                arr.slice(endIndex + 1 until endBlockEndIndex).sum()
            ) // Excluding EndIndex.
    }

    fun update(index: Int, value: Int) {
        val windowIndex = index / chunkSize
        sumArr[windowIndex] = sumArr[windowIndex] - arr[index] + value
        arr[index] = value
    }
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }
    val squareRootDecomposition = SquareRootDecompositionSum(arr as MutableList<Int>)
    squareRootDecomposition.preProcess()
    val noOfQueries = readLine()!!.trim().toInt()
    repeat(noOfQueries) {
        val (startIndex, endIndex) = readLine()!!.split(",").map { it.trim().toInt() }
        println(squareRootDecomposition.query(startIndex, endIndex))
    }
}
