package leetcode.backtracking

/**
 * https://leetcode.com/problems/permutations-ii/
 */
fun permuteUnique(nums: IntArray): List<List<Int>> =
  permuteUniqueUtil(nums.sorted()) // ! Sort to keep dups together

private fun permuteUniqueUtil(
  numsSorted: List<Int>,
  used: BooleanArray = BooleanArray(numsSorted.size),
  combination: List<Int> = emptyList()
): List<List<Int>> =
  if (combination.size == numsSorted.size) {
    listOf(combination)
  } else {
    numsSorted.indices
      // * Previous in an iteration would be unused, if its branch has finished.
      // * It's useless to start a branch with duplicate as well
      // * So skip if both previous and current are dups and both are unused.
      .filter { (!used[it] && (it == 0 || numsSorted[it] != numsSorted[it - 1])) || used[it - 1] }
      .flatMap { unusedIndex ->
        used[unusedIndex] = true
        permuteUniqueUtil(numsSorted, used, combination + numsSorted[unusedIndex]).also {
          used[unusedIndex] = false
        }
      }
  }
