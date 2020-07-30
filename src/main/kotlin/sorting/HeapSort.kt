package sorting

fun IntArray.heapSort() {
    val mid = size / 2 - 1
    for (i in mid downTo 0) {
        heapify(size, i)
    }
    for (i in lastIndex downTo 0) {
        this[i] = this[0].also { this[0] = this[i] } // swap with first node, and keep compressing from the end.
        heapify(i, 0)
    }
}

fun IntArray.heapify(
    heapSize: Int,
    rootIndex: Int,
    heapComparator: Int.(Int) -> Boolean = fun Int.(b: Int) = this > b
) {
    var heapIndex = rootIndex
    val leftIndex = 2 * rootIndex + 1
    val rightIndex = 2 * rootIndex + 2
    if (leftIndex < heapSize && this[leftIndex].heapComparator(this[heapIndex])) {
        heapIndex = leftIndex
    }
    if (rightIndex < heapSize && this[rightIndex].heapComparator(this[heapIndex])) {
        heapIndex = rightIndex
    }

    if (heapIndex != rootIndex) { // Observe we use index to swap.
        this[heapIndex] = this[rootIndex].also { this[rootIndex] = this[heapIndex] }
        heapify(heapSize, heapIndex)
    }
}

fun main() {
    val nums = intArrayOf(2, 12, 89, 23, 76, 43, 12)
    nums.heapSort()
    print(nums.contentToString())
}
