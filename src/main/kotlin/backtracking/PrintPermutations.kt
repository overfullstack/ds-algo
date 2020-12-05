package backtracking

fun printPermutations(str: String, permutation: String, used: BooleanArray) {
    val strLength = str.length
    if (permutation.length == strLength) {
        println(permutation)
        return
    }
    for (i in 0 until strLength) {
        if (!used[i]) {
            used[i] = true
            printPermutations(str, permutation + str[i], used)
            used[i] = false
        }
    }
}

fun main() {
    printPermutations("abc", "", BooleanArray(3))
}
