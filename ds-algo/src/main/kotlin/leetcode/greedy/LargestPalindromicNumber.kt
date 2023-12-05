package leetcode.greedy

/** https://leetcode.com/problems/largest-palindromic-number/ */
fun largestPalindromicNumber(num: String): String {
  val digitFrequency: Map<Char, Int> = num.groupingBy { it }.eachCount().toSortedMap().reversed()
  val halfOfPalindrome =
    digitFrequency.entries.fold("") { result, (digit, frequency) ->
      if (result.isEmpty() && digit == '0') {
        // * NOTE 20/11/23 gopala.akshintala: We cannot return "0" here, as the result can be empty
        // coz other digits may have frequency = 1
        result
      } else {
        result + digit.toString().repeat(frequency / 2)
      }
    }
  val maxDigitWithOddFrequency: Char? =
    digitFrequency.entries.firstOrNull { it.value % 2 != 0 }?.key
  return if (
    halfOfPalindrome.isEmpty() &&
      (maxDigitWithOddFrequency == null || maxDigitWithOddFrequency == '0')
  ) {
    "0"
  } else {
    halfOfPalindrome + (maxDigitWithOddFrequency?.toString() ?: "") + halfOfPalindrome.reversed()
  }
}
