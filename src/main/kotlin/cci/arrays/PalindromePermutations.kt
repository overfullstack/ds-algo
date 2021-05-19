package cci.arrays

fun isPalidromePermutation(str: String): Boolean {
    val freqMap = mutableMapOf<Char, Int>()
    var oddCount = 0
    for (ch in str) {
        val oldFreq = freqMap.get(ch) ?: 0
        val newFreq = oldFreq.inc()
        if (newFreq % 2 == 0) {
            oddCount--
        } else {
            oddCount++
        }
        freqMap.put(ch, newFreq)
    }
    return oddCount <= 1
}
