package utils

/* 27 Aug 2024 17:36 */

fun <T> IndexedValue<T>.toPair(): Pair<Int, T> = component1() to component2()

fun <T> List<T>.toPair(): Pair<T, T> =
  when {
    size > 2 ->
      throw IllegalArgumentException("List with more than 2 elements cannot be converted to Pair")
    else -> first() to last()
  }

fun <T> List<T>.toTriple(): Triple<T, T, T> =
  when {
    size > 3 ->
      throw IllegalArgumentException("List with more than 2 elements cannot be converted to Pair")
    else -> Triple(get(0), get(1), get(2))
  }
