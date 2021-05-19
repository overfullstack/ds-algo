package searching

fun IntArray.binarySearch(key: Int): Int {
    var left = 0
    var right = lastIndex
    while (left < right) {
        val mid = (left + right) / 2
        val compare = this[mid].compareTo(key)
        when {
            compare > 0 -> right = mid - 1
            compare < 0 -> left = mid + 1
            else -> return mid
        }
    }
    return -1
}

fun main() {
    val list = intArrayOf(10, 8, 18, 45, 63, 49, 88, 15, 62)
    print(list.binarySearch(18))
}
