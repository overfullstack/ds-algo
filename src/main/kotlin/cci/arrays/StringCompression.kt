package cci.arrays

fun compressString(str: String): String {
    var ptr1 = -1
    var ptr2 = 0
    var compressedStr = ""
    while (ptr2 <= str.lastIndex) {
        while (ptr2 + 1 <= str.lastIndex && str[ptr2] == str[ptr2 + 1]) {
            ptr2++
        }
        val len = ptr2 - ptr1
        compressedStr = compressedStr + str[ptr2] + len
        ptr1 = ptr2
        ptr2++
    }
    return if (compressedStr.length < str.length) compressedStr else str
}
