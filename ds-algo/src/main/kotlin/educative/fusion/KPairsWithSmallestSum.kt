package educative.fusion

import java.util.PriorityQueue

/* 21 Jul 2025 10:24 */

fun kPairsWithSmallestSum(nums1: IntArray, nums2: IntArray, k: Int): List<Pair<Int, Int>> {
  val minHeap = PriorityQueue(Comparator.comparingInt<Triple<Int, Int, Int>> { it.first })
  minHeap.addAll(
    nums1.take(maxOf(k, nums1.size)).withIndex().map { (index, num) ->
      Triple(num + nums2[0], index, 0)
    }
  )
  var counter = 0
  val result = mutableListOf<Pair<Int, Int>>()
  while (minHeap.isNotEmpty() && counter <= k) {
    val (_, nums1Index, nums2Index) = minHeap.poll()
    result += (nums1[nums1Index] to nums2[nums2Index])
    if (nums2Index + 1 <= nums2.lastIndex) {
      minHeap.add(Triple(nums1[nums1Index] + nums2[nums2Index + 1], nums1Index, nums2Index + 1))
    }
    counter++
  }
  return result
}
