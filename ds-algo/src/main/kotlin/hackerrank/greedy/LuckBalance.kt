/* gakshintala created on 12/25/19 */
package hackerrank.greedy

fun luckBalance(k: Int, contests: Array<Pair<Int, Int>>): Int {
  return contests.sumOf { it.first } -
    2 * contests.filter { it.second == 1 }.sortedBy { it.first }.dropLast(k).sumOf { it.first }
}

fun main() {
  val (noOfContests, maxImportantContests) =
    readln().split(" ").map { it.trim().toInt() }.zipWithNext().first()
  print(
    luckBalance(
      maxImportantContests,
      Array(noOfContests) { readln().split(" ").map { it.trim().toInt() }.zipWithNext().first() },
    )
  )
}
