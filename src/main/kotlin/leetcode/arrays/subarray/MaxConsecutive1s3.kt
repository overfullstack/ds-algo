package leetcode.arrays.subarray

fun longestOnes(nums: IntArray, k: Int): Int {
    var zeroCount = 0
    var start = 0
    var maxWindow = Int.MIN_VALUE
    for ((i, num) in nums.withIndex()) {
        if (num == 0) zeroCount++
        // This kicks-in the moment zeroCount is above k, from then on `start` moves in the same freq as `i`.
        // That's why we have `if` instead of `while`
        // That is the whole window is shifted to right by 1
        // So the maxWindow recorded remains constant
        if (zeroCount > k) {
            if (nums[start] == 0) zeroCount--
            start++
        }
        maxWindow = i - start + 1 // This is either increasing or constant, so don't need `maxOf`
    }
    return maxWindow
}
