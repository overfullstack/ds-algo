package cci.sortandsearch

fun sortedMerge(a: IntArray, b: IntArray): IntArray {
    var aIndex = a.lastIndex
    var bIndex = b.lastIndex
    var mergedIndex = aIndex + bIndex - 1

    while (bIndex >= 0) {
        if (aIndex >= 0 && a[aIndex] > b[bIndex]) {
            a[mergedIndex] = a[aIndex]
            aIndex--
        } else {
            a[mergedIndex] = b[bIndex]
            bIndex--
        }
        mergedIndex--
    }
    return a
}
