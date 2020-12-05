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
): List<List<Int>> {
    if (startIndex == nums.lastIndex) {
        return listOf(combination + nums[startIndex])
    }
    return (startIndex..nums.lastIndex).fold(emptyList()) { results, index ->
        results + if (index == startIndex || nums[index] != nums[index - 1]) {
            val curCombination = combination + nums[index]
            listOf(curCombination) + subsetsWithDupUtil(nums, index + 1, curCombination)
        } else {
            emptyList()
        }
    }
}
