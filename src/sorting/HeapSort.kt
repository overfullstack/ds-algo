package sorting

import java.util.*

fun IntArray.heapSort() {
    val mid = size / 2 - 1
    for (i in mid downTo 0) {
        heapify(size, i)
    }
    for (i in size - 1 downTo 0) {
        this[i] = this[0].also { this[0] = this[i] }
        heapify(i, 0)
    }
}

fun IntArray.heapify(heapSize: Int, rootIndex: Int) {
    var largestIndex = rootIndex
    val leftIndex = 2 * rootIndex + 1
    val rightIndex = 2 * rootIndex + 2
    if (leftIndex < heapSize && this[leftIndex] > this[largestIndex]) {
        largestIndex = leftIndex
    }
    if (rightIndex < heapSize && this[rightIndex] > this[largestIndex]) {
        largestIndex = rightIndex
    }

    if (largestIndex != rootIndex) {
        this[largestIndex] = this[rootIndex].also { this[rootIndex] = this[largestIndex] }
        heapify(heapSize, largestIndex)
    }
}

fun main() {
    val nums = intArrayOf(2, 12, 89, 23, 76, 43, 12)
    nums.heapSort()
    print(Arrays.toString(nums))
}