package leetcode.arrays.slidingwindow.string

fun lengthOfLongestSubstringAtmostKDistinct(s: String, k: Int): String {
    val freqInWindow = mutableMapOf<Char, Int>()
    var start = 0
    var maxWindowLen = Int.MIN_VALUE
    var maxWindowStart = 0
    for ((index, char) in s.withIndex()) {
        freqInWindow.merge(char, 1) { freq, _ -> freq.inc() }

        while (freqInWindow.size > k) {
            val curWindowLen = index - start // ! No +1 as we exclude the char which incresed size
            if (curWindowLen > maxWindowLen) {
                maxWindowStart = start
                maxWindowLen = curWindowLen
            }
            freqInWindow.compute(s[start]) { _, freq ->
                when (freq) {
                    1 -> null
                    else -> freq!!.dec()
                }
            }
            start++
        }
    }

    return if (maxWindowLen == Int.MIN_VALUE) s else s.substring(maxWindowStart, maxWindowStart + maxWindowLen)
}

fun main() {
    println(lengthOfLongestSubstringAtmostKDistinct("aabbcc", 3))
}
