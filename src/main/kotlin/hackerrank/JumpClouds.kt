/* gakshintala created on 8/8/19 */
package hackerrank

fun jumpingOnClouds(c: Array<Int>): Int {
    val jumpTable = IntArray(c.size) { 0 }
    if (c[0] == 1 || c.last() == 1) {
        return -1
    }
    jumpTable[0] = 0
    jumpTable[1] = if (c[1] == 1) Int.MAX_VALUE else 1

    for (i in 2..jumpTable.lastIndex) {
        jumpTable[i] = if (c[i] == 1) Int.MAX_VALUE else minOf(jumpTable[i - 1], jumpTable[i - 2]) + 1
    }
    return jumpTable[jumpTable.lastIndex]
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        val arr = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
        println(jumpingOnClouds(arr))
    }
}