package leetcode.dp

/* 02 Aug 2025 17:53 */

/**
 * [1043. Partition Array for Maximum
 * Sum](https://leetcode.com/problems/partition-array-for-maximum-sum/)
 */
fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
  val dp = IntArray(arr.size + 1)
  for (i in 1..(arr.lastIndex + 1)) {
    var j = 1
    var maxForNewPartition = Int.MIN_VALUE
    // ! Moving last `k` elements into this partition to check they increase the max
    while (j <= k && (i - j) >= 0) {
      maxForNewPartition = maxOf(maxForNewPartition, arr[i - j])
      // ! As per problem, each subarray has their values changed to become
      // the maximum value of that subarray.
      dp[i] = maxOf(dp[i], dp[i - j] + maxForNewPartition * j)
      j++
    }
  }
  return dp.last()
}

fun main() {
  println(maxSumAfterPartitioning(intArrayOf(1, 15, 7, 9, 2, 5, 10), 3))
}
