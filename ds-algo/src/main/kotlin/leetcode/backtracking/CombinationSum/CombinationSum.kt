/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

/** https://leetcode.com/problems/combination-sum/ */
fun IntArray.combinationSum(
  sumLeft: Int,
  startIndex: Int = 0,
  combination: List<Int> = emptyList()
): List<List<Int>> =
  when {
    sumLeft < 0 -> emptyList()
    sumLeft == 0 -> listOf(combination)
    else ->
      (startIndex..lastIndex).flatMap {
        combinationSum(
          sumLeft - this[it],
          it, // passing same `index` instead of `index+1`, as problem allows repetition.
          combination + this[it]
        )
      }
  }

fun main() {
  val candidates = readln().split(",").map { it.trim().toInt() }
  val target = readln().toInt()
  candidates.toIntArray().combinationSum(target).forEach(::println)
}
