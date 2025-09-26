package leetcode.backtracking

/** [78. Subsets](https://leetcode.com/problems/subsets) */
fun subsets(
  nums: IntArray,
  combination: List<Int> = emptyList(),
  startIndex: Int = 0,
): List<List<Int>> =
  // It's like each index is supposed to produce a list per branch.
  (startIndex..nums.lastIndex).flatMap { index ->
    val curCombination = combination + nums[index]
    // Every letter starts a branch for following letters, like a -> bcd, b -> cd,
    // Appending a current combination for all results from its children.
    listOf(curCombination) + subsets(nums, curCombination, index + 1)
  }

fun subsets(nums: IntArray): List<List<Int>> = subsets(nums, emptyList(), 0) + listOf(emptyList())

fun main() {
  println(subsets(intArrayOf(1, 2, 3))) // [[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]]
}
