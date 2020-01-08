/* gakshintala created on 12/7/19 */
package leetcode.backtracking.CombinationSum

private fun combinationSum(
    arr: IntArray,
    sumLeft: Int,
    startIndex: Int = 0,
    combination: List<Int> = emptyList()
): List<List<Int>> {
    if (sumLeft < 0) {
        return emptyList()
    }
    if (sumLeft == 0) {
        return listOf(combination)
    }
    return (startIndex..arr.lastIndex).fold(emptyList()) { results, index ->
        results + combinationSum(
            arr,
            sumLeft - arr[index],
            index, // passing same `index` instead of `index+1` as repetition is allowed
            combination + arr[index]
        ) 
    }
}

fun main() {
    val candidates = readLine()!!.split(",").map { it.trim().toInt() }
        .sorted().toIntArray() // **** Sort the array before calling. Sorting is to keep all duplicates together ****
    val target = readLine()!!.toInt()
    combinationSum(candidates, target).forEach(::println)
} 