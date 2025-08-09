package leetcode.array

/* 09 Aug 2025 18:58 */

/**
 * [299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows/)
 */
// Approach 1: Two-pass with frequency counting (most readable)
fun getHint(secret: String, guess: String): String {
    val bulls: Int = secret.zip(guess).count { (s, g) -> s == g }
    val secretFreq: Map<Char, Int> = secret.groupingBy { it }.eachCount()
    val guessFreq: Map<Char, Int> = guess.groupingBy { it }.eachCount()
    val totalMatches: Int = secretFreq.keys.intersect(guessFreq.keys)
        .sumOf { digit -> minOf(secretFreq[digit]!!, guessFreq[digit]!!) }
    val cows: Int = totalMatches - bulls
    return "${bulls}A${cows}B"
}

fun getHintOnePass(secret: String, guess: String): String {
  val secretFreq = mutableMapOf<Char, Int>()
  val guessFreq = mutableMapOf<Char, Int>()
  var bulls = 0
  var cows = 0
  for (i in secret.indices) {
    when {
      secret[i] == guess[i] -> bulls++
      else -> {
        secretFreq.merge(secret[i], 1, Int::plus)
        guessFreq.merge(guess[i], 1, Int::plus)
        val compute = { _: Char, v: Int -> if (v == 1) null else v.dec() }
        if (guess[i] in secretFreq) {
          cows++
          secretFreq.computeIfPresent(guess[i], compute)
          guessFreq.computeIfPresent(guess[i], compute)
        }
        if (secret[i] in guessFreq) {
          cows++
          secretFreq.computeIfPresent(secret[i], compute)
          guessFreq.computeIfPresent(secret[i], compute)
        }
      }
    }

  }
  return "${bulls}A${cows}B"
}

fun main() {
  // Input: secret = "1807", guess = "7810"
  println(getHint("1807", "7810"))
  // Input: secret = "1123", guess = "0111"
  println(getHint("1123", "0111"))
}
