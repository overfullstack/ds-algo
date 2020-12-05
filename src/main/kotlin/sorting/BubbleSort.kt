package sorting

fun bubbleSort(arr: IntArray): IntArray {
    val len = arr.size
    for (i in 0 until len) {
        for (j in 0 until len - 1) {
            if (arr[j] > (arr[j + 1])) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    return arr
}

fun main() {
    val nums = intArrayOf(2, 12, 89, 23, 76, 43, 12)
    print(bubbleSort(nums).contentToString())
}
