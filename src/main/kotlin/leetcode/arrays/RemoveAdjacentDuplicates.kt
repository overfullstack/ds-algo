/* gakshintala created on 2/4/20 */
package leetcode.arrays

fun removeDuplicates(originalStr: String): String {
    val result = CharArray(originalStr.length)
    var resultIndex = 0
    for (char in originalStr) {
        result[resultIndex] = char
        if (resultIndex > 0 && result[resultIndex - 1] == result[resultIndex]) {
            resultIndex -= 2
        }
        resultIndex++
    }
    return result.take(resultIndex).joinToString("")
}

fun removeKDuplicates(originalStr: String, k: Int): String {
    if (k == 1) {
        return originalStr
    }
    val result = CharArray(originalStr.length)
    val count = IntArray(originalStr.length)
    var resultIndex = 0
    for (char in originalStr) {
        result[resultIndex] = char
        if (resultIndex > 0 && result[resultIndex - 1] == result[resultIndex]) {
            count[resultIndex] =
                count[resultIndex - 1] + 1 // We need an array, coz as new chars get concatenated we need to count them along with previous characters 
            if (count[resultIndex] == k) {
                resultIndex -= k
            }
        } else {
            count[resultIndex] = 1
        }
        resultIndex++
    }
    return result.take(resultIndex).joinToString("")
}
