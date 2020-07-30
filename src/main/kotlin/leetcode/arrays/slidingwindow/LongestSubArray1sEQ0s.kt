package leetcode.arrays.slidingwindow

/**
 * https://leetcode.com/problems/contiguous-array/
 * We need to find longest contiguous subarray with equal number of 0s and 1s
 */
fun longestContiguousSubArray(nums: IntArray): Int {
    var max = 0
    val countMap = mutableMapOf(0 to -1)
    var count = 0
    for (i in nums.indices) {
        count += (if (nums[i] == 1) 1 else -1)
        // If you encountered equal no.of 0s or 1s to bring back the count
        countMap.merge(count, i) { lastOccurance, curOccurance ->
            max = maxOf(max, curOccurance - lastOccurance)
            lastOccurance
        }
    }
    return max
}
