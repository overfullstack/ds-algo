/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

private fun combinationSum2(
    arr: List<Int>,
    sumLeft: Int,
    startIndex: Int = 0,
    combination: IntArray = IntArray(0)
): List<IntArray> {
    if (sumLeft < 0) {
        return emptyList()
    }
    if (sumLeft == 0) {
        return listOf(combination)
    }
    return (startIndex..arr.lastIndex).fold(emptyList()) { results, index ->
        // As this is sorted, all duplicates appear next to each other. For every index, allow the first one and skip all the following duplicates.
        results + if (index == startIndex || arr[index] != arr[index - 1]) {
            combinationSum2(arr, sumLeft - arr[index], index + 1, combination + arr[index])
        } else {
            emptyList()
        }
    }
}

fun main() {
    val candidates = readLine()!!.split(",").map { it.trim().toInt() }
        .sorted() // **** Sort the array before calling. Sorting is to keep all duplicates together ****
    val target = readLine()!!.toInt()
    combinationSum2(candidates, target).forEach { println(it.joinToString(",")) }
} 