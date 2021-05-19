/* gakshintala created on 12/29/19 */
package leetcode.backtracking

fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    nums.sort()
    return subsetsWithDupUtil(nums) + emptyList()
}

private fun subsetsWithDupUtil(
    nums: IntArray,
    startIndex: Int = 0,
    combination: List<Int> = emptyList()
): List<List<Int>> =
    if (startIndex == nums.lastIndex) {
        listOf(combination + nums[startIndex])
    } else {
        (startIndex..nums.lastIndex)
            .filter { it == startIndex || nums[it] != nums[it - 1] }
            .flatMap {
                val curCombination = combination + nums[it]
                listOf(curCombination) + subsetsWithDupUtil(nums, it + 1, curCombination)
            }
    }
