package leetcode.sortandsearch

fun findMinInSortedRotated2(arr: IntArray, left: Int = 0, right: Int = arr.lastIndex): Int {
    if (arr[left] <= arr[right]) {
        return arr[left]
    }
    val mid = (left + right) / 2
    return when {
        arr[left] < arr[mid] -> findMinInSortedRotated2(arr, mid + 1, right)
        arr[left] > arr[mid] -> findMinInSortedRotated2(arr, left, mid - 1)
        arr[right] != arr[mid] -> findMinInSortedRotated2(
            arr,
            mid + 1,
            right
        ) // implicit arr[mid] == left
        else -> arr[mid]
    }
}
