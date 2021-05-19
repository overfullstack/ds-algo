/* gakshintala created on 12/30/19 */
package leetcode.arrays

fun maximumSwap(num: Int): Int {
    val numCharArray = num.toString().toCharArray()
    val buckets = IntArray(10)
    for ((digitIndex, digitChar) in numCharArray.withIndex()) {
        buckets[digitChar - '0'] = digitIndex
    }

    for ((digitIndex, digitChar) in numCharArray.withIndex()) { // Highest valued digit should be swapped first.
        val digitValue = digitChar - '0' + 1
        // Checking buckets from last to first until curDigit bucket,
        // As all buckets after curDigit bucket have numbers greater than curDigit char.
        for (bucketIndex in 9 downTo digitValue) {
            val greaterDigitIndex = buckets[bucketIndex]
            if (greaterDigitIndex > digitIndex) { // Both value and index are greater, we found a highest number after current digit.
                numCharArray[greaterDigitIndex] =
                    numCharArray[digitIndex].also {
                        numCharArray[digitIndex] = numCharArray[greaterDigitIndex]
                    }
                return numCharArray.joinToString("").toInt()
            }
        }
    }
    return num
}
