/* gakshintala created on 1/2/20 */
package leetcode.sortandsearch

fun medianWithDifferentSize(a: IntArray, b: IntArray): Double {
    if (a.size > b.size) {
        return medianWithDifferentSize(b, a)
    }

    var low = 0
    var high = a.size
    val mergedMid = (a.size + b.size + 1) / 2 // if (a.size + b.size) is odd, then left side will have an element more 

    while (low <= high) {
        var aPartition = (low + high) / 2
        var bPartition = mergedMid - aPartition

        val aLeft = if (aPartition == 0) Int.MIN_VALUE else a[aPartition - 1]
        val aRight = if (aPartition == a.size) Int.MAX_VALUE else a[aPartition]

        val bLeft = if (bPartition == 0) Int.MIN_VALUE else b[bPartition - 1]
        val bRight = if (bPartition == b.size) Int.MAX_VALUE else b[bPartition]

        if (aLeft <= bRight && aRight >= bLeft) {
            if ((a.size + b.size) % 2 == 0) {
                return ((maxOf(aLeft, bLeft) + minOf(aRight, bRight)).toDouble() / 2)
            }
            return maxOf(aLeft, bLeft).toDouble() // max of both left because as commented about, if the a+b size is odd, left side would bear an extra element.
        } else if (aLeft > bRight) {
            high = aPartition - 1
        } else {
            low = aPartition + 1
        }
    }
    throw IllegalArgumentException()
}