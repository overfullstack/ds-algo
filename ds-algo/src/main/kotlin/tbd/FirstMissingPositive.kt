package leetcode.array

/**
 * Solution: First Missing Positive
 *
 * Algorithm Approach: In-place hash set using the array itself
 *
 * The key insight is that for an array of size n, the first missing positive integer must be in the
 * range [1, n+1]. If all numbers from 1 to n are present, then n+1 is missing.
 *
 * We use the array indices as a hash set - if we see a positive number k in range [1,n], we mark
 * position k-1 as "visited" by making the value negative.
 *
 * Why this approach? Because we need O(1) space, so we can't use a separate hash set. The array
 * itself becomes our tracking mechanism.
 */
fun firstMissingPositive(nums: IntArray): Int {
  // Edge case: empty array - smallest positive integer is 1
  if (nums.isEmpty()) return 1

  val n = nums.size

  // Step 1: Mark all non-positive numbers and numbers > n as "invalid"
  // We replace them with n+1 so they won't interfere with our marking scheme
  // This handles edge cases where array contains very large numbers or zeros
  for (i in nums.indices) {
    if (nums[i] !in 1..n) {
      nums[i] = n + 1
    }
  }

  // Step 2: Use the array as a hash set
  // For each number k in range [1,n], mark position k-1 as visited by making it negative
  // This tells us that positive integer k exists in the array
  for (i in nums.indices) {
    val absValue = kotlin.math.abs(nums[i])
    // Only process numbers in our valid range [1,n]
    if (absValue in 1..n) {
      // Mark position absValue-1 as visited by making it negative
      // Use -1 to avoid issues with -0
      nums[absValue - 1] = -kotlin.math.abs(nums[absValue - 1])
    }
  }

  // Step 3: Find the first positive number (unvisited position)
  // The first index i where nums[i] > 0 means positive integer i+1 is missing
  for (i in nums.indices) {
    if (nums[i] > 0) {
      return i + 1
    }
  }

  // If all positions from 1 to n are marked as visited,
  // then n+1 is the first missing positive integer
  return n + 1
}

fun main() {
  // Test cases
  println(firstMissingPositive(intArrayOf(1, 2, 0))) // Expected: 3
  println(firstMissingPositive(intArrayOf(3, 4, -1, 1))) // Expected: 2
  println(firstMissingPositive(intArrayOf(7, 8, 9, 11, 12))) // Expected: 1
  println(firstMissingPositive(intArrayOf(1))) // Expected: 2
  println(firstMissingPositive(intArrayOf(2))) // Expected: 1
  println(firstMissingPositive(intArrayOf(1, 1))) // Expected: 2
}
