package sorting

fun IntArray.quickSort(left: Int, right: Int) {
    if (left < right) {
        val partitionPoint = partition(left, right)
        quickSort(left, partitionPoint - 1)
        quickSort(partitionPoint + 1, right)
    }
}

fun IntArray.partition(left: Int, right: Int): Int {
    val pivot = this[right]
    var i = left - 1
    for (j in left until right) {
        if (this[j] <= pivot) {
            i++
            this[i] = this[j].also { this[j] = this[i] }
        }
    }
    this[right] = this[i + 1]
    this[i + 1] = pivot

    return i + 1
}

fun IntArray.quickSortFp(): IntArray = if (size < 2) this else {
    val pivot = first()
    val (smaller, greater) = drop(1).partition { it <= pivot }
    smaller.toIntArray().quickSortFp() + pivot + greater.toIntArray().quickSortFp()
}

fun main() {
    val arr = intArrayOf(10, 8, 18, 45, 63, 49, 88, 15, 62)
    arr.quickSortFp().forEach { print(" $it") }
}
