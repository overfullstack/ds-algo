package hackerrank


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
    val valueToIndex = arr.mapIndexed { index, value -> value to index }.toTypedArray()
    valueToIndex.sortBy { it.first }
    // Sort array first and try and recreate the original one, noting down all the swaps in-between
    for (i in valueToIndex.indices) {
        if (valueToIndex[i].second != i) {
            while (valueToIndex[i].second != i) {
                valueToIndex[i] =
                    valueToIndex[valueToIndex[i].second].also { valueToIndex[valueToIndex[i].second] = valueToIndex[i] }
                swaps++
            }
        }
    }
    return swaps
}
