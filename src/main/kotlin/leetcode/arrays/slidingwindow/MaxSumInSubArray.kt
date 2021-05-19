package leetcode.arrays.slidingwindow

fun maxSumInSubArray(nums: IntArray): Int {
    var sum = 0
    var maxSum = Int.MIN_VALUE
    for (num in nums) {
        sum += num
        maxSum = maxOf(maxSum, sum)
        if (sum < 0) {
            sum = 0
        }
    }
    return maxSum
}
