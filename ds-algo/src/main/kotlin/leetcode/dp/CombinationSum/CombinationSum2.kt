/* gakshintala created on 12/7/19 */
package leetcode.dp.CombinationSum

/** [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) */
fun combinationSum(arr: IntArray, targetSum: Int): List<IntArray> =
  // ! Sort the array before calling. Sorting is to keep all duplicates together
  combinationSumInternal(arr.sorted(), targetSum)

private fun combinationSumInternal(
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
        // ! Starting branches with same element leads to duplicate combinations.
        // ! So at any recursion level we allow an element only once in the iteration
        // ! `it == startIndex` lets you include the duplicate in the combination
        // ! in the next recursion level's first iteration
        .filter { it == startIndex || arrSorted[it] != arrSorted[it - 1] }
        .flatMap {
          combinationSumInternal(
            arrSorted,
            sumLeft - arrSorted[it],
            it + 1,
            combination + arrSorted[it],
          )
        }
  }

fun main() {
  /*  val candidates =
    readln()
      .split(",")
      .map { it.trim().toInt() }.toIntArray()
  val target = readln().toInt()*/
  println(combinationSum(intArrayOf(1, 1, 2), 3).joinToString(" || ") { it.joinToString() })
  println(
    combinationSum(intArrayOf(10, 1, 2, 7, 6, 1, 5), 8).joinToString(" || ") { it.joinToString() }
  )
}
