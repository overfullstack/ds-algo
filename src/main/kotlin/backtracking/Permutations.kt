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

fun permute(
    nums: IntArray,
    permutation: List<Int> = emptyList(),
    used: BooleanArray = BooleanArray(nums.size)
): List<List<Int>> {
    if (permutation.size == nums.size) {
        return listOf(permutation)
    }
    return nums.foldIndexed(emptyList()) { index, results, num ->
        results + if (!used[index]) {
            used[index] = true
            permute(nums, permutation + num, used).also { used[index] = false }
        } else {
            emptyList()
        }
    }
}

fun main() {
    printPermutations("abc", "", BooleanArray(3))
}