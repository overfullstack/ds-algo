package leetcode.array

/* 01 Aug 2025 13:26 */

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

fun numMatchingSubseq2(s: String, words: Array<String>): Int {
  val buckets = mutableMapOf<Char, ArrayDeque<Pair<String, Int>>>()
  for (c in 'a'..'z') buckets[c] = ArrayDeque()

  for (word in words) buckets[word[0]]!!.add(word to 0)

  var count = 0
  for (c in s) {
    val queue = buckets[c]!!
    repeat(queue.size) {
      val (word, idx) = queue.removeFirst()
      val next = idx + 1
      if (next == word.length) count++ else buckets[word[next]]!!.add(word to next)
    }
  }

  return count
}

fun main() {
  // println(numMatchingSubseq("abcde", arrayOf("a", "bb", "acd", "ace")))
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
  )
}
