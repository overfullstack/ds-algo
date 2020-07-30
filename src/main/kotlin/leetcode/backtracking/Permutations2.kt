package leetcode.backtracking

fun permuteUnique(nums: IntArray): List<List<Int>> = permuteUniqueUtil(nums.sorted())

private fun permuteUniqueUtil(
    numsSorted: List<Int>,
    used: BooleanArray = BooleanArray(numsSorted.size),
    combination: List<Int> = emptyList()
): List<List<Int>> =
    if (combination.size == numsSorted.size) {
        listOf(combination)
    } else {
        numsSorted.indices
            // If all duplicates are unused, only one of them passes through the filter.
            // Other duplicates get a chance only in next recursions.
            .filter { !used[it] && (it == 0 || numsSorted[it] != numsSorted[it - 1]) || used[it - 1] }
            .flatMap { unusedIndex ->
                used[unusedIndex] = true
                permuteUniqueUtil(numsSorted, used, combination + numsSorted[unusedIndex]).also {
                    used[unusedIndex] = false
                }
            }
    }
