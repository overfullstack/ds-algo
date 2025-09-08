/* gakshintala created on 12/8/19 */
package leetcode.dp

/** [97. Interleaving String](https://leetcode.com/problems/interleaving-string/) */
fun interLeavingStrings(str1: String, str2: String, str3: String): Boolean {
  if (str3.length != str1.length + str2.length) {
    return false
  }
  val table = BooleanArray(str2.length + 1)

  table[0] = true
  // ! Dealing with Single letters
  for (i in 1..table.lastIndex) { // ! Is `str3` made of only `str1`
    table[0] = table[0] && str3[i - 1] == str1[i - 1]
  }
  for (j in 1..table.lastIndex) {
    table[j] = table[j - 1] && str3[j - 1] == str2[j - 1]
  }

  // ! Starting with 2 char strings (i + j)
  // ! Keep str1 constant at i and increment str2 j and check interleaving
  for (i in 1..str1.length) {
    for (j in 1..str2.length) {
      // ! `table[j]` here represents previous results
      // ! with `i-1` chars from `str1` and `j` chars from `str2`
      // ! `table[i] or table[j-1] may be true due to any of the strings char match
      val isCharFromStr1 = table[j] && (str3[i + j - 1] == str1[i - 1])
      val isCharFromStr2 = table[j - 1] && (str3[i + j - 1] == str2[j - 1])
      table[j] = isCharFromStr2 || isCharFromStr1
    }
  }
  return table.last()
}

fun main() {
  println(interLeavingStrings(readln(), readln(), readln()))
}
