package backtracking

fun combinations(str: String, combination: String = "", startIndex: Int = 0): List<String> =
    // It's like each index is supposed to produce a list per branch.
    (startIndex..str.lastIndex).flatMap { index -> 
      val curCombination = combination + str[index]
      // Every letter starts a branch for following letters, like a -> bcd, b -> cd,
      // Appending a current combination for all results from its children.
      listOf(curCombination) + combinations(str, curCombination, index + 1)
    }

fun main() {
  println(combinations("abc").joinToString(", "))
}
