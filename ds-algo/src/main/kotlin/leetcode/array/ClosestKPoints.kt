package leetcode.array

fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> =
  kClosest(points.map { it[0] to it[1] }, K).map { intArrayOf(it.first, it.second) }.toTypedArray()

private tailrec fun kClosest(
  points: List<Pair<Int, Int>>,
  K: Int,
  result: List<Pair<Int, Int>> = emptyList(),
): List<Pair<Int, Int>> {
  val (lower, higher) = points.partition { it <= points[points.lastIndex] }

  return when {
    lower.size > K -> kClosest(lower.dropLast(1), K, result)
    lower.size < K -> kClosest(higher, K - lower.size, result + lower)
    else -> result + lower
  }
}

private operator fun Pair<Int, Int>.compareTo(other: Pair<Int, Int>): Int =
  (first * first + second * second) - (other.first * other.first + other.second * other.second)

fun main() {
  println(kClosest(listOf(-2 to 10, -4 to -8, 10 to 7, -4 to -7), 3))
}
