/* gakshintala created on 12/25/19 */
package hackerrank.greedy

fun luckBalance(k: Int, contests: Array<Pair<Int, Int>>): Int {
    return contests.sumBy { it.first } - 2 * contests.filter { it.second == 1 }
        .sortedBy { it.first }.dropLast(k).sumBy { it.first }
}

fun main() {
    val (noOfContests, maxImportantContests) = readLine()!!.split(" ").map { it.trim().toInt() }.zipWithNext().first()
    print(
        luckBalance(
            maxImportantContests,
            Array(noOfContests) { readLine()!!.split(" ").map { it.trim().toInt() }.zipWithNext().first() }
        ))
}
