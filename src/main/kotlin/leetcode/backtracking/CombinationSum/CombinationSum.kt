/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

fun combinationSum(
    arr: IntArray,
    sumLeft: Int,
    startIndex: Int = 0,
    combination: List<Int> = emptyList()
): List<List<Int>> =
    when {
        sumLeft < 0 -> emptyList()
        sumLeft == 0 -> listOf(combination)
        else -> (startIndex..arr.lastIndex).flatMap {
            combinationSum(
                arr,
                sumLeft - arr[it],
                it, // passing same `index` instead of `index+1`, as problem allows repetition.
                combination + arr[it]
            )
        }
    }

fun main() {
    val candidates = readLine()!!.split(",").map { it.trim().toInt() }
    val target = readLine()!!.toInt()
    combinationSum(candidates.toIntArray(), target).forEach(::println)
}
