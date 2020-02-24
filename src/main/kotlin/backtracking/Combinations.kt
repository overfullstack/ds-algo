package backtracking

fun printCombinations(str: String, combination: String, startIndex: Int) {
    for (index in startIndex until str.length) { // No need of used[], as startIndex prevents usage all those that are already used in the combination.
        println(combination + str[index])
        combinations(str, combination + str[index], index + 1)
    }
}

fun combinations(str: String, combination: String = "", startIndex: Int = 0): List<String> {
    if (startIndex > str.lastIndex) {
        return listOf(combination)
    }
    return (startIndex..str.lastIndex).fold(emptyList()) { results, index ->
        val curCombination = combination + str[index]
        results + curCombination + combinations(str, curCombination, index + 1)
    }
}

fun main() {
    combinations("abc", "", 0)
}
