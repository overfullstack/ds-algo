package backtracking

fun combinations(str: String, res: String, startIndex: Int) {
    var combination = res
    for (i in startIndex until str.length) {
            combination += str[i]
            println(combination)
            combinations(str, combination, i + 1)
            combination = combination.dropLast(1) // You drop the last, so it gets replaced by next in the loop.
    }
}

fun main() {
    combinations("abc", "", 0)
}