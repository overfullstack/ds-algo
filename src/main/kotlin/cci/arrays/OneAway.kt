package cci.arrays

fun isOneAway(str1: String, str2: String): Boolean {
    if ((str1.length == str2.length) || (str1.length + 1 == str2.length)) {
        return checkIsOneAway(str1, str2)
    } else if (str2.length + 1 == str1.length) {
        return checkIsOneAway(str2, str1)
    }
    return false
}

private fun checkIsOneAway(str1: String, str2: String): Boolean {
    var index1 = 0
    var index2 = 0
    var diffFound = false
    while (index1 < str1.length && index2 < str2.length) {
        if (str1[index1] != str2[index2]) {
            if (diffFound) {
                return false
            }
            diffFound = true
            if (str1.length == str2.length) {
                index1++
            }
        } else {
            index1++
        }
        index2++
    }
    return true
}
