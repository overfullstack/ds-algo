/* gakshintala created on 9/8/19 */
package queryoptmization

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

class SparseTableRMQ(var arr: IntArray) {
  private var sparseTable = Array(arr.size) { IntArray(ceil(log2(arr.size.toFloat())).toInt()) }

  fun process() {
    sparseTable.forEachIndexed { index, row -> row[0] = index }

    for (sparseInterval in 1 until sparseTable[0].size) {
      for (row in sparseTable.indices) { // row represent array indeces
        val halfIndex = row + 2.0.pow(sparseInterval - 1).toInt()
        if (halfIndex < arr.size) {
          sparseTable[row][sparseInterval] =
            if (
              arr[sparseTable[row][sparseInterval - 1]] <=
                arr[sparseTable[halfIndex][sparseInterval - 1]]
            )
              sparseTable[row][sparseInterval - 1]
            else sparseTable[halfIndex][sparseInterval - 1]
        }
      }
    }
  }

  fun rmqIndex(startIndex: Int, endIndex: Int): Int {
    val sparseInterval = log2((abs(endIndex - startIndex) + 1).toFloat()).toInt()
    val firstHalfRange = sparseTable[startIndex][sparseInterval]
    val secondHalfRange =
      sparseTable[endIndex - 2.0.pow(sparseInterval).toInt() + 1][sparseInterval]
    return if (arr[firstHalfRange] <= arr[secondHalfRange]) firstHalfRange else secondHalfRange
  }
}

fun main() {
  val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
  val sparseTableRMQ = SparseTableRMQ(arr)
  sparseTableRMQ.process()
  val noOfQueries = readLine()!!.trim().toInt()
  val queries = mutableListOf<Pair<Int, Int>>()
  repeat(noOfQueries) {
    val (startIndex, endIndex) = readLine()!!.split(",").map { it.trim().toInt() }
    queries.add(Pair(startIndex, endIndex))
  }
  queries.forEach { println(arr[sparseTableRMQ.rmqIndex(it.first, it.second)]) }
}
