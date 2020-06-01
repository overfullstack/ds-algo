/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

// **** Sort the array before calling. Sorting is to keep all duplicates together ****
fun combinationSum2(arr: IntArray, targetSum: Int): List<IntArray> = combinationSum2(arr.sorted(), targetSum)

fun combinationSum2(
    arr: List<Int>,
    sumLeft: Int,
    startIndex: Int = 0,
    combination: IntArray = IntArray(0)
): List<IntArray> =
    when {
        sumLeft < 0 -> emptyList()
        sumLeft == 0 -> listOf(combination)
        else -> (startIndex..arr.lastIndex).filter { it == startIndex || arr[it] != arr[it - 1] }.flatMap {
            combinationSum2(arr, sumLeft - arr[it], it + 1, combination + arr[it])
        }
    }

fun main() {
    val candidates = readLine()!!.split(",").map { it.trim().toInt() }
        .sorted() // **** Sort the array before calling. Sorting is to keep all duplicates together ****
    val target = readLine()!!.toInt()
    combinationSum2(candidates, target).forEach { println(it.joinToString(",")) }
}
