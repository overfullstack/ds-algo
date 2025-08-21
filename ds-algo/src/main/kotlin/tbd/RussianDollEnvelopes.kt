package leetcode.dp

/**
 * Solution: Russian Doll Envelopes
 *
 * Algorithm Approach: Sort + Longest Increasing Subsequence (LIS)
 *
 * The key insight is that this problem reduces to finding the longest increasing subsequence after
 * sorting the envelopes. We sort by width in ascending order, and for envelopes with the same
 * width, we sort by height in descending order.
 *
 * Why this approach? After sorting, we can use the LIS algorithm on heights to find the maximum
 * number of envelopes that can be nested. The descending height sort for same width ensures we
 * don't incorrectly count envelopes with equal widths as nestable.
 *
 * This is a classic example of reducing a 2D problem to a 1D problem through clever sorting.
 */
fun maxEnvelopes(envelopes: Array<IntArray>): Int {
  // Edge case: empty array
  if (envelopes.isEmpty()) return 0

  // Step 1: Sort envelopes by width (ascending) and height (descending for same width)
  // This sorting is crucial because:
  // - Ascending width ensures we process envelopes from smallest to largest
  // - Descending height for same width prevents incorrect nesting of equal-width envelopes
  val sortedEnvelopes =
    envelopes
      .asSequence()
      .sortedWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })
      .toList()

  // Step 2: Extract heights and find Longest Increasing Subsequence (LIS)
  // We only need heights because after sorting, width ordering is guaranteed
  val heights = sortedEnvelopes.map { it[1] }

  return findLIS(heights)
}

/**
 * Find the length of the Longest Increasing Subsequence using binary search
 *
 * This is an optimized O(n log n) approach that maintains a "tails" array where tails[i] represents
 * the smallest possible tail value for a subsequence of length i+1
 */
private fun findLIS(nums: List<Int>): Int {
  if (nums.isEmpty()) return 0

  val tails = mutableListOf<Int>()

  nums.forEach { num ->
    // Binary search to find the position to insert current number
    val insertPos = findInsertPosition(tails, num)

    if (insertPos == tails.size) {
      // Current number is larger than all tails, extend the sequence
      tails.add(num)
    } else {
      // Replace the tail at insertPos with current number
      // This maintains the smallest possible tail for that length
      tails[insertPos] = num
    }
  }

  // The length of tails array gives us the LIS length
  return tails.size
}

/**
 * Binary search to find the position where 'num' should be inserted in 'tails'
 *
 * This function finds the first position where tails[pos] >= num If no such position exists, it
 * returns tails.size
 */
private fun findInsertPosition(tails: List<Int>, num: Int): Int {
  var left = 0
  var right = tails.size

  while (left < right) {
    val mid = left + (right - left) / 2

    when {
      tails[mid] < num -> left = mid + 1
      else -> right = mid
    }
  }

  return left
}

fun main() {
  // Test cases
  println(
    maxEnvelopes(arrayOf(intArrayOf(5, 4), intArrayOf(6, 4), intArrayOf(6, 7), intArrayOf(2, 3)))
  ) // Expected: 3 (2,3 -> 5,4 -> 6,7)

  println(
    maxEnvelopes(arrayOf(intArrayOf(1, 1), intArrayOf(1, 1), intArrayOf(1, 1)))
  ) // Expected: 1 (no nesting possible)

  println(
    maxEnvelopes(
      arrayOf(
        intArrayOf(1, 3),
        intArrayOf(3, 5),
        intArrayOf(6, 7),
        intArrayOf(6, 8),
        intArrayOf(8, 9),
        intArrayOf(9, 10),
      )
    )
  ) // Expected: 4 (1,3 -> 3,5 -> 6,7 -> 8,9)

  println(
    maxEnvelopes(
      arrayOf(
        intArrayOf(2, 100),
        intArrayOf(3, 200),
        intArrayOf(4, 300),
        intArrayOf(5, 500),
        intArrayOf(5, 400),
        intArrayOf(5, 250),
        intArrayOf(6, 370),
        intArrayOf(6, 360),
        intArrayOf(7, 380),
      )
    )
  ) // Expected: 5
}
