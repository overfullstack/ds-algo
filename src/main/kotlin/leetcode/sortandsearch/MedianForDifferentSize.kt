/* gakshintala created on 1/2/20 */
package leetcode.sortandsearch

fun medianWithDifferentSize(a: IntArray, b: IntArray): Double {
    if (a.size > b.size) {
        return medianWithDifferentSize(b, a) // Convention - `a` is Smaller
    }

    var low = 0
    var high = a.size
    // Coz of +1, if (a.size + b.size) is odd, the left side will have an element more
    val mergedMid = (a.size + b.size + 1) / 2

    while (low <= high) {
        // This is more like binary search and has nothing to do with the search item being a median
        val aPartition = (low + high) / 2 // Driven always by the smaller array `a`.
        val bPartition = mergedMid - aPartition // (left on a + left on b) | (right on a + right on b)

        // Just values on and left (off by -1) partition with edge cases 0, size.
        // Partition is not actually a line between two elements,
        // so it shall be on an element which is treated as being on the right side of | and left is partition-1
        val aLeft = if (aPartition == 0) Int.MIN_VALUE else a[aPartition - 1]
        val aOnPartition = if (aPartition == a.size) Int.MAX_VALUE else a[aPartition]

        val bLeft = if (bPartition == 0) Int.MIN_VALUE else b[bPartition - 1]
        val bOnPartition = if (bPartition == b.size) Int.MAX_VALUE else b[bPartition]

        // This is to separate out left part and right part in merged array
        // (0-aLeft) (aOnPartition - end)
        // (0-bLeft) (bOnPartition - end)
        when {
            aLeft <= bOnPartition && aOnPartition >= bLeft -> // Found the right paritition
                when ((a.size + b.size) % 2) { // is size even or odd
                    // maxOf and minOf to determine who is closest to the merged partition.
                    0 -> (maxOf(aLeft, bLeft) + minOf(aOnPartition, bOnPartition)).toDouble() / 2

                    // max of both left to check which is the middle most, as commented above, if the `a + b` size is odd, left side would bear an extra element.
                    else -> maxOf(aLeft, bLeft).toDouble()
                }
            aLeft > bOnPartition -> high = aPartition - 1 // Go to left of `a` for partition
            else -> low = aPartition + 1 // Go to right of `a` for partition
        }
    }
    throw IllegalArgumentException("Code shouldn't come till here")
}
