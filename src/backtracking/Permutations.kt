package backtracking

fun permutations(str: String, res: String, used: BooleanArray) {
    var result = res
    val strLength = str.length
    if (res.length == strLength) {
        println(res)
        return
    }
    for (i in 0 until strLength) {
        if (!used[i]) {
            result += str[i]
            used[i] = true
            permutations(str, result, used)
            used[i] = false
            result = result.dropLast(1)
        }
    }
}

fun main() {
    permutations("abc", "", BooleanArray(3))
}