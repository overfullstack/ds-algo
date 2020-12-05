package sorting

fun IntArray.insertionSort() {
    for (i in 1 until size) {
        val key = this[i]
        var j = i - 1
        while (j >= 0 && this[j] > key) {
            this[j + 1] = this[j]
            j--
        }
        this[j + 1] = key
    }
}

fun main() {
    val nums = intArrayOf(2, 12, 89, 23, 76, 43, 12)
    nums.insertionSort()
    nums.forEach { print(" $it") }
}
