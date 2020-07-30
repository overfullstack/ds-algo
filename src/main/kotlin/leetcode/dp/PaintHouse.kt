package leetcode.dp

fun minCosts(costs: Array<IntArray>): Int {
    if (costs.isEmpty()) {
        return 0
    }
    for (i in 1..costs.lastIndex) {
        costs[i][0] = minOf(costs[i - 1][1], costs[i - 1][2])
        costs[i][1] = minOf(costs[i - 1][0], costs[i - 1][2])
        costs[i][2] = minOf(costs[i - 1][0], costs[i - 1][1])
    }
    return minOf(costs[costs.lastIndex][0], costs[costs.lastIndex][1], costs[costs.lastIndex][2])
}
