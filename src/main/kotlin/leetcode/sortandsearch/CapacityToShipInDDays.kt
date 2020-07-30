package leetcode.sortandsearch

fun shipWithinDays(weights: IntArray, D: Int, minCap: Int = weights.maxOrNull()!!, maxCap: Int = weights.sum()): Int {
    if (minCap == maxCap) { // * Can't squeeze anymore, or only one item
        return minCap
    }
    var curCap = (minCap + maxCap) / 2
    var daysRequired = 0
    weights.reduce { curSum, weight ->
        if (curSum + weight > curCap) {
            daysRequired++
            weight
        } else {
            curSum + weight
        }
    }
    return when {
        daysRequired > D -> shipWithinDays(weights, D, curCap + 1, maxCap)
        // ! Observe no curCap - 1 for right, as it is curCap or less
        else -> shipWithinDays(
            weights,
            D,
            minCap,
            curCap
        ) // * If daysRequired is equal or less, we squeeze till we get the min Cap
    }
}
