package leetcode.dp

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
fun canPartition(nums: IntArray): Boolean {
    val sum = nums.sum()
    if (sum % 2 == 1) {
        return false
    }
    val halfSum = sum / 2
    val table = BooleanArray(halfSum + 1)
    table[0] = true
    // * Can I make half-sum with some of the numbers. Rest of the numbers should obviously make the other half to equate total sum.
    for (num in nums) {
        for (j in halfSum downTo num) { // * Fwd iteration won't work as table[0] will effect subsequent results in table.
            table[j] = table[j] or table[j - num]
        }
    }
    return table[halfSum]
}
