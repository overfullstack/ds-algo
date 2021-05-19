package hackerrank.arrays

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
        println(minSwapsToSort(arr))
    }
}

fun minSwapsToSort(arr: Array<Int>): Int {
    var swaps = 0
    val valueToIndex = arr.mapIndexed { index, value -> index to value }.toTypedArray()
    // * Sort array first and try to recreate the original one, noting down all the swaps in-between
    valueToIndex.sortBy { it.second }
    for (i in valueToIndex.indices) {
        while (valueToIndex[i].first != i) { // Swapping until the original index matches with the current index.
            valueToIndex[i] = // Swapping in a cycle - to original index until it finds a match.
                valueToIndex[valueToIndex[i].first].also {
                    valueToIndex[valueToIndex[i].first] = valueToIndex[i]
                }
            swaps++
        }
    }
    return swaps
}
