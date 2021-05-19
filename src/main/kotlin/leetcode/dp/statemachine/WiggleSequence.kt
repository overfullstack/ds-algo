package leetcode.dp

fun wiggleMaxLength(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }

    var up = 1
    var down = 1

    for (i in 1..nums.lastIndex) {
        // up or down remain the same if their counterpart don't contribute to a wiggle.
        // So for a constantly increasing sequence, the result would only be 2, as down won't increase.
        // * State Transition - `up` transists from `down` and `down` transists from `up`
        // * `up` happens if prev is `down` and `down` happens if prev is `up`
        when {
            nums[i] > nums[i - 1] -> up = down + 1
            nums[i] < nums[i - 1] -> down = up + 1
        }
    }
    return maxOf(up, down)
}
