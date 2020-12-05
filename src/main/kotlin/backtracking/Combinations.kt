package backtracking

fun printCombinations(str: String, combination: String, startIndex: Int) {
    for (index in startIndex until str.length) { // No need of used[], as startIndex prevents usage all those that are already used in the combination.
        println(combination + str[index])
        printCombinations(str, combination + str[index], index + 1)
    }
}

fun combinations(str: String, combination: String = "", startIndex: Int = 0): List<String> =
    (startIndex..str.lastIndex).flatMap { // It's like each index is supposed to produce a list per branch.
        val curCombination = combination + str[it]
        // Every letter starts a branch for following letters, like a -> bcd, b -> cd
        // Appending current combination for all results from its children.
        combinations(str, curCombination, it + 1) + curCombination
    }

fun main() {
    printCombinations("abcd", "", 0)
}
