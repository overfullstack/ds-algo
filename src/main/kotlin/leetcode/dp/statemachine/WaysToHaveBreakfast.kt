package leetcode.dp

/**
 * https://leetcode.com/discuss/interview-question/726978/number-of-ways-to-have-breakfast-microsoft/610618
 * Bread - dialy, Pizza - alternate, Burger - Every 2 days
 */
fun waysToHaveBreakfast(n: Int): Int {
    val table = Array(n + 1) { IntArray(3) }

    table[1][0] = 1
    table[1][1] = 1
    table[1][2] = 1

    for (i in 2..n) {
        table[i][0] = table[i - 1][0] + table[i - 1][1] + table[i - 1][2] // Yesterday had anything
        table[i][1] = table[i - 1][0] + table[i - 1][2] // Yesterday had either Bread or Burger
        // Yesterday Bread or Pizza. But from above equations, their calculation include Burger from
        // yesterday and day-before-yesterday, so it needs to be negated.
        table[i][2] = table[i - 1][0] + table[i - 1][1] - 2 * table[i - 2][2]
    }
    return table[n][0] + table[n][1] + table[n][2]
}

fun main() {
    println(waysToHaveBreakfast(2)) // 7
    println(waysToHaveBreakfast(3)) // 15
}
