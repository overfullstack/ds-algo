package leetcode.stack

import java.util.Stack

/**
 * https://leetcode.com/problems/remove-k-digits/
 */
fun removeKdigits(num: String, k: Int): String {
    if (k == num.length) {
        return "0"
    }
    var removals = 0
    val stk = Stack<Char>()
    val numWithoutLeadingZeros = num.dropWhile { it == '0' }
    if (numWithoutLeadingZeros.isEmpty()) {
        return "0"
    }

    stk.push(numWithoutLeadingZeros[0])
    for (digit in numWithoutLeadingZeros.drop(1)) {
        // For Higher places, Digits are replaced by subsequent lower digits.
        while (removals < k && stk.isNotEmpty() && stk.peek() > digit) {
            stk.pop()
            removals++
        }
        stk.push(digit)
    }

    while (removals < k && stk.isNotEmpty()) {
        stk.pop()
        removals++
    }

    return stk.joinToString("").dropWhile { it == '0' }.ifEmpty { "0" }
}
