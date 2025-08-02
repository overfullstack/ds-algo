package leetcode.dp

/* 02 Aug 2025 17:53 */

fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
  val dp = IntArray(arr.size + 1)
  for (i in 1..(arr.lastIndex + 1)) {
    var j = 1
    var maxForNewPartition = Int.MIN_VALUE
    while (j <= k && (i - j) >= 0) {
      maxForNewPartition = maxOf(maxForNewPartition, arr[i - j])
      dp[i] = maxOf(dp[i], dp[i - j] + maxForNewPartition * j)
      j++
    }
  }
  return dp.last()
}

fun main() {
  println(maxSumAfterPartitioning(intArrayOf(1, 15, 7, 9, 2, 5, 10), 3))
}
