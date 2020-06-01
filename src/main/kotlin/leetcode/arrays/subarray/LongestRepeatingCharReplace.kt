package leetcode.arrays.subarray

fun characterReplacement(s: String, k: Int): Int {
    var start = 0
    var maxWindow = 0
    val freqMap = IntArray(26)
    var maxCharFreq = Int.MIN_VALUE
    for ((i, char) in s.withIndex()) {
        freqMap[char - 'A']++
        maxCharFreq = maxOf(maxCharFreq, freqMap[char - 'A'])
        if ((i - start + 1) > k + maxCharFreq) {
            freqMap[s[start] - 'A']--
            start++
        }
        maxWindow = i - start + 1
    }
    return maxWindow
}
