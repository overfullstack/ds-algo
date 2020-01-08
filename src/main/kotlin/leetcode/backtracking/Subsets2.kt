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
    return (startIndex..nums.lastIndex).foldIndexed(emptyList()) { index, results, num ->
        results + if (index == startIndex || num != nums[index - 1]) {
            val curCombination = combination + num
            listOf(curCombination) + subsetsWithDupUtil(nums, index + 1, curCombination)
        } else {
            emptyList()
        }
    }
}