package leetcode.array

/* 04 Aug 2025 13:04 */

fun numberOfWeakCharacters(properties: Array<IntArray>): Int {
  val sortedProperties =
    properties.sortedWith(Comparator.comparingInt<IntArray> { it[0] }.thenByDescending { it[1] })
  return sortedProperties
    .dropLast(1)
    .foldRight(sortedProperties.last()[1] to 0) { property, (max, count) ->
      when {
        property[1] < max -> max to (count + 1)
        else -> property[1] to count
      }
    }
    .second
}

fun main() {
  println("hi")
  println(numberOfWeakCharacters(arrayOf(intArrayOf(5, 5), intArrayOf(6, 3), intArrayOf(3, 6))))
  println(numberOfWeakCharacters(arrayOf(intArrayOf(2, 2), intArrayOf(3, 3))))
}
