package cci.recursion

tailrec fun searchMagicIndexNonRepeating(arr: IntArray, low: Int = 0, high: Int = arr.lastIndex): Int {
    if (low > high) {
        return -1
    }
    val mid = (low + high) / 2
    return when {
        mid > arr[mid] -> searchMagicIndexNonRepeating(arr, low, mid - 1)
        mid < arr[mid] -> searchMagicIndexNonRepeating(arr, mid + 1, high)
        else -> arr[mid]
    }
}

fun findMagicIndexRepeating(arr: IntArray, low: Int = 0, high: Int = arr.lastIndex): Int {
    if (low > high) {
        return -1
    }
    val mid = (low + high) / 2
    if (arr[mid] == mid) {
        return mid
    }
    val leftBound = minOf(mid - 1, arr[mid]) // If `arr[mid] < mid`, you may skip all the indices till `index=arr[mid]`.
    val leftResult = findMagicIndexRepeating(arr, low, leftBound)
    if (leftResult >= 0) {
        return leftResult
    }
    val rightBound = maxOf(mid + 1, arr[mid])
    val rightResult = findMagicIndexRepeating(arr, rightBound, high)
    return rightResult
}
