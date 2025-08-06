package educative.fusion

import java.util.PriorityQueue

/* 21 Jul 2025 10:24 */

fun kPairsWithSmallestSum(nums1: IntArray, nums2: IntArray, k: Int): List<Pair<Int, Int>> {
  val minHeap = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.first })
  // ! Both the arrays are sorted in this problem
  minHeap.addAll(
    nums1.take(minOf(k, nums1.size)).withIndex().map { (index, num1) ->
      Triple(num1 + nums2[0], index, 0)
    }
  )
  // * We inserted all num1 indices into heap, so they will be shuffled as per the sum
  val result = mutableListOf<Pair<Int, Int>>()
  while (minHeap.isNotEmpty() && result.size < k) {
    val (_, nums1Index, nums2Index) = minHeap.poll()
    result += (nums1[nums1Index] to nums2[nums2Index])
    if (nums2Index + 1 <= nums2.lastIndex) {
      minHeap.add(Triple(nums1[nums1Index] + nums2[nums2Index + 1], nums1Index, nums2Index + 1))
    }
  }
  return result
}
