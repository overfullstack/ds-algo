/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

/** https://leetcode.com/problems/combination-sum-ii/ */
// * Sort the array before calling. Sorting is to keep all duplicates together *
fun combinationSum2(arr: IntArray, targetSum: Int): List<IntArray> =
  combinationSum2(arr.sorted(), targetSum)

fun combinationSum2(
  arrSorted: List<Int>,
  sumLeft: Int,
  startIndex: Int = 0,
  combination: IntArray = IntArray(0),
): List<IntArray> =
  when {
    sumLeft < 0 -> emptyList()
    sumLeft == 0 -> listOf(combination)
    else ->
      (startIndex..arrSorted.lastIndex)
        // ! This is not to avoid duplicates in one combination but required to avoid Duplicate
        // combination in the final list.
        .filter { it == startIndex || arrSorted[it] != arrSorted[it - 1] }
        .flatMap {
          combinationSum2(arrSorted, sumLeft - arrSorted[it], it + 1, combination + arrSorted[it])
        }
  }

fun main() {
  val candidates =
    readln()
      .split(",")
      .map { it.trim().toInt() }
      .sorted() // * Sort the array before calling. Sorting is to keep all duplicates together *
  val target = readln().toInt()
  combinationSum2(candidates, target).forEach { println(it.joinToString()) }
}
