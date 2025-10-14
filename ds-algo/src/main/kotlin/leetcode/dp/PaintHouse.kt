package leetcode.dp

/** [515 · Paint House](https://www.lintcode.com/problem/515) */
fun minCosts(costs: Array<IntArray>): Int {
  if (costs.isEmpty()) {
    return 0
  }
  for (i in 1..costs.lastIndex) { // ! Notice `+=`
    costs[i][0] += minOf(costs[i - 1][1], costs[i - 1][2])
    costs[i][1] += minOf(costs[i - 1][0], costs[i - 1][2])
    costs[i][2] += minOf(costs[i - 1][0], costs[i - 1][1])
  }
  return minOf(costs[costs.lastIndex][0], costs[costs.lastIndex][1], costs[costs.lastIndex][2])
}

// ! Inefficient for large inputs, throws StackOverFlow
fun minCostsTopDown(
  costs: Array<IntArray>,
  idx: Int = 0,
  prevColor: Int = -1, // ! Some Invalid color to allow all 3 colors for first iteration
  memo: Array<IntArray> = Array(costs.size) { IntArray(4) },
): Int =
  when {
    idx == costs.lastIndex + 1 -> 0
    memo[idx][prevColor + 1] != 0 -> memo[idx][prevColor + 1]
    else ->
      (0 until 3)
        .filter { it != prevColor }
        .minOf { curColor ->
          costs[idx][curColor] + minCostsTopDown(costs, idx + 1, curColor, memo)
        }
        .also { memo[idx][prevColor + 1] = it }
  }

fun main() {
  println(
    minCosts(arrayOf(intArrayOf(14, 2, 11), intArrayOf(11, 14, 5), intArrayOf(14, 3, 10)))
  ) // 10
  println(minCosts(arrayOf(intArrayOf(1, 2, 3), intArrayOf(1, 4, 6)))) // 3

  println(
    minCostsTopDown(arrayOf(intArrayOf(14, 2, 11), intArrayOf(11, 14, 5), intArrayOf(14, 3, 10)))
  ) // 10
  println(minCostsTopDown(arrayOf(intArrayOf(1, 2, 3), intArrayOf(1, 4, 6)))) // 3
}
