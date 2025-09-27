/* gakshintala created on 12/7/19 */
package leetcode.dp.CombinationSum

/** https://leetcode.com/problems/combination-sum/ */
fun combinationSumDP(coins: IntArray, target: Int): Set<List<Int>> {
  val dp = Array<Set<List<Int>>>(target + 1) { emptySet() }
  dp[0] = setOf(emptyList())
  // * DP is being built from start to end
  for (coin in coins) {
    for (i in coin..target) {
      dp[i] += dp[i - coin].map { it + coin }.toSet()
    }
  }
  return dp[target]
}

fun combinationSum(
  arr: IntArray,
  target: Int,
  startIndex: Int = 0,
  combination: List<Int> = emptyList(),
): List<List<Int>> =
  when {
    target < 0 -> emptyList()
    target == 0 -> listOf(combination)
    else ->
      (startIndex..arr.lastIndex).flatMap {
        // passing same `index` instead of `index+1`, as problem allows repetition.
        combinationSum(arr, target - arr[it], it, combination + arr[it])
      }
  }

fun main() {
  /*val candidates = readln().split(",").map { it.trim().toInt() }
  val target = readln().toInt()
  combinationSum(candidates.toIntArray(), target).forEach(::println)*/
  println(combinationSumDP(intArrayOf(2, 3, 6, 7), 7))
}
