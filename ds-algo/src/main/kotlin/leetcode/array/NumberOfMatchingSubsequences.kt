package leetcode.array

/* 01 Aug 2025 13:26 */

/**
 * [792. Number of Matching
 * Subsequences](https://leetcode.com/problems/number-of-matching-subsequences)
 */
fun numMatchingSubseq(s: String, words: Array<String>): Int {
  val buckets: MutableMap<Char, ArrayDeque<Pair<Int, String>>> =
    words.groupBy({ it.first() }, { 0 to it }).mapValues { ArrayDeque(it.value) }.toMutableMap()
  var count = 0
  for (ch in s) {
    buckets[ch]?.let { queue ->
      repeat(queue.size) {
        val (index, word) = queue.removeFirst()
        val next = index + 1
        when {
          // * The ch in s, has to hunt down a word in every bucket it gets placed, to finish it
          next == word.length -> count++
          else ->
            buckets.merge(word[next], ArrayDeque(listOf(next to word))) { old, _ ->
              old.add(next to word)
              old
            }
        }
      }
    }
  }
  return count
}

fun main() {
  println(numMatchingSubseq("abcde", arrayOf("a", "bb", "acd", "ace"))) // 3 ("a", "acd", "ace")
  println(
    numMatchingSubseq(
      "rwpddkvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvjubjgloeofnpjqlkdsqvruvabjrikfwronbrdyyjnakstqjac",
      arrayOf(
        "wpddkvbnn",
        "lnagtva",
        "kvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvju",
        "rwpddkvbnnugln",
        "gloeofnpjqlkdsqvruvabjrikfwronbrdyyj",
        "vbgeinupkvgmgxeaaiuiyojmoqkahwvbpwugdainxciedbdkos",
        "mspuhbykmmumtveoighlcgpcapzczomshiblnvhjzqjlfkpina",
        "rgmliajkiknongrofpugfgajedxicdhxinzjakwnifvxwlokip",
        "fhepktaipapyrbylskxddypwmuuxyoivcewzrdwwlrlhqwzikq",
        "qatithxifaaiwyszlkgoljzkkweqkjjzvymedvclfxwcezqebx",
      ),
    )
  ) // 5 ("lnagtva", "vbgeinupkvgmg", "gloeofnpjqlkds", "mspuhbykmmumt", "rgmliajkiknong")
}
