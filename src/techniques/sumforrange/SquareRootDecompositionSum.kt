/* gakshintala created on 9/6/19 */
package techniques.sumforrange

import kotlin.math.sqrt

class SquareRootDecompositionSum(var arr: MutableList<Int>) {
    private var sumArr = mutableListOf<Int>()
    private var windowSize: Int = 0

    fun process() {
        windowSize = sqrt(arr.size.toDouble()).toInt()
        sumArr = arr.asSequence()
            .windowed(windowSize, windowSize, true)
            .map { it.sum() }
            .toMutableList()
    }

    fun query(startIndex: Int, endIndex: Int): Int {
        if (startIndex > endIndex || startIndex < 0 || endIndex > arr.size - 1) {
            return -1
        }

        val startBlock = startIndex / windowSize
        val endBlock = endIndex / windowSize

        return (sumArr.slice(startBlock..endBlock).sum()
                - arr.slice(startBlock * windowSize until startIndex).sum() // Excluding StartIndex.
                - arr.slice(endIndex + 1..endBlock * windowSize).sum()) // Excluding EndIndex.
    }
    
    fun update(index: Int, value: Int) {
        val windowIndex = index / windowSize
        sumArr[windowIndex] = sumArr[windowIndex] - arr[index] + value
        arr[index] = value
    }
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }
    val squareRootDecomposition = SquareRootDecompositionSum(arr as MutableList<Int>)
    squareRootDecomposition.process()
    val noOfQueries = readLine()!!.trim().toInt()
    repeat(noOfQueries) {
        val (startIndex, endIndex) = readLine()!!.split(",").map { it.trim().toInt() }
        println(squareRootDecomposition.query(startIndex, endIndex))
    }
}