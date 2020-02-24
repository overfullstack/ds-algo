/* gakshintala created on 12/30/19 */
package leetcode.arrays

fun maximumSwap(num: Int): Int {
    val numCharArray = num.toString().toCharArray()
    val buckets = IntArray(10)
    for ((digitIndex, digitChar) in numCharArray.withIndex()) {
        buckets[digitChar - '0'] = digitIndex
    }

    for ((digitIndex, digitChar) in numCharArray.withIndex()) { 
        for (bucketIndex in 9 downTo digitChar - '0' + 1) { // Checking buckets from last to first until curDigit bucket, as all buckets after curDigit bucket have numbers greater than curDigit char. 
            val greaterDigitIndex = buckets[bucketIndex]
            if (greaterDigitIndex > digitIndex) { // if any greater digitIndex falls after the current digitIndex, swap both
                numCharArray[greaterDigitIndex] =
                    numCharArray[digitIndex].also { numCharArray[digitIndex] = numCharArray[greaterDigitIndex] }
                return numCharArray.joinToString("").toInt()
            }
        }
    }
    return num
}
