package leetcode.dp

/** [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) */
fun canPartition(nums: IntArray): Boolean {
  val sum = nums.sum()
  if (sum % 2 == 1) {
    return false
  }
  val halfSum = sum / 2
  val table = BooleanArray(halfSum + 1)
  table[0] = true
  // * Can I make half-sum with some numbers? The rest of the numbers should obviously make the
  // other half to equate to a total sum.
  for (num in nums) {
    // ! Backward iteration, as it's a `0/1 knapsack` problem,
    // where each number can be used at most once
    // when updating table[j], you only reference table[j - num] which hasn't been updated
    // in the current iteration
    for (j in halfSum downTo num) {
      table[j] = table[j] or table[j - num]
    }
  }
  return table[halfSum]
}

fun main() {
  println(canPartition(intArrayOf(1, 2, 5))) // false
}
