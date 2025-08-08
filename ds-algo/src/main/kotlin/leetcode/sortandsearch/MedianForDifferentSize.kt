/* gakshintala created on 1/2/20 */
package leetcode.sortandsearch

/** https://leetcode.com/problems/median-of-two-sorted-array/ */
fun findMedianSortedArrays(a: IntArray, b: IntArray): Double {
  if (a.size > b.size) {
    // Convention - `a` is Smaller.
    // This gives better time complexity as we do binary search on smaller array to find partition
    // elements.
    return findMedianSortedArrays(b, a)
  }

  var low = 0
  // Have an extra element on left of the partition if size is even
  // Coz of +1, if (a.size + b.size) is odd, the left side will have an element more
  var high = a.lastIndex + 1
  // say a.size + b.size = 9, we add 1 to push partition to 5 instead of 4
  val mergedMid = (a.size + b.size + 1) / 2

  while (low <= high) {
    // This is more like binary search on smaller array to find right partition elements.
    // Both aPartition and mergedMid has an extra element on left if size is even.
    val aPartition = (low + high) / 2
    val bPartition = mergedMid - aPartition // (left on a + left on b) | (right on a + right on b)

    // Partition is not actually a line between two elements,
    // It shall be on an element which is treated as being on the right side of | and left is
    // partition-1
    // Since high for aPartition and mergedMid start from lastIndex + 1, there is a chance the
    // binary search may land on this.
    val aLeft = if (aPartition == 0) Int.MIN_VALUE else a[aPartition - 1]
    val aOnPartition = if (aPartition == (a.lastIndex + 1)) Int.MAX_VALUE else a[aPartition]

    val bLeft = if (bPartition == 0) Int.MIN_VALUE else b[bPartition - 1]
    val bOnPartition = if (bPartition == (b.lastIndex + 1)) Int.MAX_VALUE else b[bPartition]

    // This is to separate out left part and right part in merged array
    // (0-aLeft) (aOnPartition - end)
    // (0-bLeft) (bOnPartition - end)
    when {
      aLeft <= bOnPartition && aOnPartition >= bLeft -> { // ! Median found
        if ((a.size + b.size) % 2 == 0) {
          // maxOf and minOf to determine who is closest to the merged partition.
          return ((maxOf(aLeft, bLeft) + minOf(aOnPartition, bOnPartition)).toDouble() / 2)
        }
        // As commented above, if the `a + b` size is odd, left side would bear an extra element.
        // max of both left to check which is the middle most.
        return maxOf(aLeft, bLeft).toDouble()
      }
      aLeft > bOnPartition -> high = aPartition - 1
      else -> low = aPartition + 1
    }
  }
  throw IllegalArgumentException("Code shouldn't reach here")
}
