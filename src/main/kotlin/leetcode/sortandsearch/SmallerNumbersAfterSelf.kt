/* gakshintala created on 1/3/20 */
package leetcode.sortandsearch

fun countSmaller(nums: IntArray): List<Int> {
    val countForIndex = IntArray(nums.size)
    splitAndMerge(nums, countForIndex)
    return countForIndex.toList()
}

private fun splitAndMerge(
    nums: IntArray,
    countForIndex: IntArray,
    start: Int = 0,
    end: Int = nums.lastIndex,
    indexes: IntArray = IntArray(nums.size) { it }
) {
    if (start >= end) {
        return
    }
    val mid = (start + end) / 2
    splitAndMerge(nums, countForIndex, start, mid, indexes)
    splitAndMerge(nums, countForIndex, mid + 1, end, indexes)
    mergeSortWithIndexes(start, end, nums, indexes, countForIndex)
}

private fun mergeSortWithIndexes(
    start: Int,
    end: Int,
    nums: IntArray,
    indexes: IntArray,
    countForIndex: IntArray
) {
    val mid = (start + end) / 2
    var leftIndex = start
    var rightIndex = mid + 1
    var mergeIndex = 0
    var mergedIndexes = IntArray(end - start + 1)
    var count = 0

    while (leftIndex <= mid && rightIndex <= end) {
        if (nums[indexes[rightIndex]] < nums[indexes[leftIndex]]) { // No <= because if right = left, we don't count++ as smaller element for right side
            mergedIndexes[mergeIndex] = indexes[rightIndex]
            count++ // Doing count++ for only right side, because this problem is about finding smaller numbers on right side.
            rightIndex++
        } else {
            mergedIndexes[mergeIndex] = indexes[leftIndex]
            countForIndex[indexes[leftIndex]] += count
            leftIndex++
        }
        mergeIndex++
    }

    while (leftIndex <= mid) {
        mergedIndexes[mergeIndex] = indexes[leftIndex]
        countForIndex[indexes[leftIndex]] += count
        leftIndex++
        mergeIndex++
    }

    while (rightIndex <= end) {
        mergedIndexes[mergeIndex] = indexes[rightIndex]
        rightIndex++
        mergeIndex++
    }

    for (i in start..end) {
        indexes[i] = mergedIndexes[i - start]
    }
}
