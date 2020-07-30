package leetcode.sortandsearch

fun findMinInSortedRoated2(arr: IntArray, left: Int = 0, right: Int = arr.lastIndex): Int {
    if (arr[left] <= arr[right]) {
        return arr[left]
    }
    val mid = (left + right) / 2
    return when {
        arr[left] < arr[mid] -> findMinInSortedRoated2(arr, mid + 1, right)
        arr[left] > arr[mid] -> findMinInSortedRoated2(arr, left, mid - 1)
        arr[right] != arr[mid] -> findMinInSortedRoated2(arr, mid + 1, right) // implicit arr[mid] == left
        else -> arr[mid]
    }
}
