package leetcode.arrays.slidingwindow

/**
 * Does contain negative numbers.
 */
fun shortestSubarrayWithSumAtLeastK(nums: IntArray, k: Int): Int {
    val sumArr = nums.runningReduce(Int::plus) // Starts with 0
    // val sumArr = IntArray(nums.size + 1)
    // for ((i, num) in nums.withIndex()) {
    //     sumArr[i + 1] = sumArr[i] + num
    // }
    val dequeue = ArrayDeque<Int>() // queue contains all possible start values
    var minWindow = Int.MAX_VALUE
    for (i in sumArr.indices) {
        while (dequeue.isNotEmpty() && sumArr[i] - sumArr[dequeue.first()] >= k) { // Shrinking the window from start.
            minWindow = minOf(minWindow, i - dequeue.removeFirst()) // This starts with 0 so no +1
        }
        // This occurs due to negative numbers.
        // sumArr[i] is earlier and smaller, this makes sum bigger with shorter length for future indexes.
        while (dequeue.isNotEmpty() && sumArr[i] <= sumArr[dequeue.last()]) {
            dequeue.removeLast()
        }
        dequeue.add(i)
    }
    return if (minWindow == Int.MAX_VALUE) -1 else minWindow
}
