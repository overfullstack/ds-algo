package hackerrank

fun newYearChaos(array: IntArray): String {
    var swaps = 0
    for (i in array.indices) {
        if ((array[i] - (i + 1)) > 2) {
            return "Too chaotic"
        }
        for (j in maxOf(0, array[i] - 2) until i) {
            if (array[j] > array[i]) {
                swaps++
            }
        }
    }

    return swaps.toString()
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        println(newYearChaos(arr))
    }
}