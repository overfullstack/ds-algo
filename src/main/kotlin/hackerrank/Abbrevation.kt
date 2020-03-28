/* gakshintala created on 8/9/19 */
package hackerrank

import java.util.*

fun abbreviation(a: String, b: String): String {
    if (b.isEmpty()) {
        return if (a.all { it.isLowerCase() }) "YES" else "NO"
    }
    if (a.isEmpty()) {
        return "NO"
    }
    if (a.length < b.length) {
        return "NO"
    }
    var table = Array(a.length + 1) { BooleanArray(b.length + 1) { false } }
    table = table.map { it[0] = true; it }.toTypedArray()
    for (i in 1..a.length) {
        for (j in 1..b.length) {
            when {
                a[i - 1] == b[j - 1] -> table[i][j] = table[i - 1][j - 1]
                a[i - 1].toUpperCase() == (b[j - 1]) -> table[i][j] =
                    table[i - 1][j - 1] || table[i - 1][j] // It can be upcased or deleted
                else -> table[i][j] = if (a[i - 1].isLowerCase()) table[i - 1][j] else false
            }
        }
    }
    return if (table[a.length][b.length]) "YES" else "NO"
}

fun main() {
    val scan = Scanner(System.`in`)

    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        val a = scan.nextLine()

        val b = scan.nextLine()

        val result = abbreviation(a, b)

        println(result)
    }

}
