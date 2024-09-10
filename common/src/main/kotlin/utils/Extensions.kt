package utils

/* 27 Aug 2024 17:36 */

fun <T> IndexedValue<T>.toPair(): Pair<Int, T> = component1() to component2()

fun <T> List<T>.toPair(): Pair<T, T> =
  when {
    size > 2 ->
      throw IllegalArgumentException("List with more than 2 elements cannot be converted to Pair")
    else -> first() to last()
  }

fun Array<IntArray>.toString(): String = map { it.joinToString(" ") }.joinToString("\n")
